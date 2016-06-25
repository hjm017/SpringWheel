package com.springwheel.common.annotation;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(ApiHttpMessageConverter.class);

    protected ObjectMapper objectMapper;

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
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        logger.info("readInternal");
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        logger.info("writeInternal");
    }

    //****************************************//
    //                                        //
    //            define Method               //
    //                                        //
    //****************************************//
}
