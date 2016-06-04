package com.prototype.common.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


/**
* Class Name: MessageUtil.
* Description:
* @author hjm
*
*/
@Component
public class Messages {

    private static final Logger LOGGER = LoggerFactory.getLogger(Messages.class);

    @Autowired
    private MessageSource messageSource;

    private static Locale defaultLocale = Locale.SIMPLIFIED_CHINESE;

    /**
     * 
     * Description: get error message.
     * 
     * @param code
     * @return
     */
    public String get(String code) {
        return get(code, defaultLocale);
    }

    /**
    * Description: get error message with parameters.
    *
    * @param code
    * @param args
    * @return
    */
    public String get(String code, Object... args) {
        return get(code, defaultLocale, args);
    }

    
    /**
    * Description: get error message with locale and parameters.
    *
    * @param code
    * @param locale
    * @param args
    * @return
    */
    public  String get(String code, Locale locale, Object... args) {
        LOGGER.debug("getMessage() invoked,  Message code: " + code);
        return messageSource.getMessage(code, args, "Unknown message, code: " + code, locale);
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
