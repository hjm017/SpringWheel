package com.springwheel.common.method;


import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.ClassUtils;
import org.springframework.util.TypeUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicReference;









/**
 * @author hjm
 * @Time 2016/6/25 8:34.
 */
public class ApiHttpMessageConverter extends AbstractGenericHttpMessageConverter<Object>
        implements GenericHttpMessageConverter<Object> {

    private static Logger logger = LoggerFactory.getLogger(ApiHttpMessageConverter.class);

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    // Check for Jackson 2.3's overloaded canDeserialize/canSerialize variants with cause reference
    private static final boolean jackson23Available = ClassUtils.hasMethod(ObjectMapper.class,
            "canDeserialize", JavaType.class, AtomicReference.class);

    // Check for Jackson 2.6+ for support of generic type aware serialization of polymorphic collections
    private static final boolean jackson26Available = ClassUtils.hasMethod(ObjectMapper.class,
            "setDefaultPrettyPrinter", PrettyPrinter.class);


    private static ObjectMapper objectMapper =  new ObjectMapper();

    static {
        //空对象不出错
        //carmelMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //忽略未知属性
//         objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //驼峰属性转下划线属性
        objectMapper
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        //空格转空字符
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object paramT, JsonGenerator paramJsonGenerator,
                                  SerializerProvider paramSerializerProvider) throws IOException,
                    JsonProcessingException {
                paramJsonGenerator.writeString("");
            }
        });

    }


    //****************************************//
    //                                        //
    //            Override Method             //
    //                                        //
    //****************************************//

    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        JavaType javaType = getJavaType(type, contextClass);
        return readJavaType(javaType, inputMessage);
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        logger.info("readInternal");

        JavaType javaType = getJavaType(clazz, null);
        return readJavaType(javaType, inputMessage);
    }


    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        logger.info("writeInternal");

        JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
        JsonGenerator generator = this.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
        try {
            writePrefix(generator, object);

            Class<?> serializationView = null;
            FilterProvider filters = null;
            Object value = object;
            JavaType javaType = null;
            if (object instanceof MappingJacksonValue) {
                MappingJacksonValue container = (MappingJacksonValue) object;
                value = container.getValue();
                serializationView = container.getSerializationView();
                filters = container.getFilters();
            }
            if (jackson26Available && type != null && value != null && TypeUtils.isAssignable(type, value.getClass())) {
                javaType = getJavaType(type, null);
            }
            ObjectWriter objectWriter;
            if (serializationView != null) {
                objectWriter = this.objectMapper.writerWithView(serializationView);
            }
            else if (filters != null) {
                objectWriter = this.objectMapper.writer(filters);
            }
            else {
                objectWriter = this.objectMapper.writer();
            }
            if (javaType != null && javaType.isContainerType()) {
                objectWriter = objectWriter.withType(javaType);
            }
            objectWriter.writeValue(generator, value);

            writeSuffix(generator, object);
            generator.flush();

        }
        catch (JsonProcessingException ex) {
            throw new HttpMessageNotWritableException("Could not write content: " + ex.getMessage(), ex);
        }
    }

    //****************************************//
    //                                        //
    //            define Method               //
    //                                        //
    //****************************************//

    private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) {
        try {
            if (inputMessage instanceof MappingJacksonInputMessage) {
                Class<?> deserializationView = ((MappingJacksonInputMessage) inputMessage).getDeserializationView();
                if (deserializationView != null) {
                    return this.objectMapper.readerWithView(deserializationView).withType(javaType).
                            readValue(inputMessage.getBody());
                }
            }
            return this.objectMapper.readValue(inputMessage.getBody(), javaType);
        }
        catch (IOException ex) {
            throw new HttpMessageNotReadableException("Could not read document: " + ex.getMessage(), ex);
        }
    }

    protected JavaType getJavaType(Type type, Class<?> contextClass) {
        TypeFactory tf = this.objectMapper.getTypeFactory();
        // Conditional call because Jackson 2.7 does not support null contextClass anymore
        // TypeVariable resolution will not work with Jackson 2.7, see SPR-13853 for more details
        return (contextClass != null ? tf.constructType(type, contextClass) : tf.constructType(type));
    }

    /**
     * Write a prefix before the main content.
     * @param generator the generator to use for writing content.
     * @param object the object to write to the output message.
     */
    protected void writePrefix(JsonGenerator generator, Object object) throws IOException {
    }

    /**
     * Write a suffix after the main content.
     * @param generator the generator to use for writing content.
     * @param object the object to write to the output message.
     */
    protected void writeSuffix(JsonGenerator generator, Object object) throws IOException {
    }

    protected JsonEncoding getJsonEncoding(MediaType contentType) {
        if (contentType != null && contentType.getCharSet() != null) {
            Charset charset = contentType.getCharSet();
            for (JsonEncoding encoding : JsonEncoding.values()) {
                if (charset.name().equals(encoding.getJavaName())) {
                    return encoding;
                }
            }
        }
        return JsonEncoding.UTF8;
    }



}
