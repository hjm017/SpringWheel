package com.springwheel.common.annotation;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * @author hjm
 * @Time 2016/6/25 8:34.
 */
public class ApiHttpMessageConverter extends AbstractHttpMessageConverter {

    protected ObjectMapper objectMapper;

    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return super.canRead(clazz, mediaType);
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return super.canWrite(clazz, mediaType);
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
