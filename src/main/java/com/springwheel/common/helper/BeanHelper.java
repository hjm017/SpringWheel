package com.springwheel.common.helper;

import com.springwheel.common.mapper.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: hejm
 * Date: 2016/7/1
 * Time: 14:08
 */
public class BeanHelper {

    private static Logger logger = LoggerFactory.getLogger(BeanHelper.class);

    /**
     * 对象属性拷贝
     * @param oldBean
     * @param newBean
     */
    public static void copyProperty(Object oldBean, Object newBean) {
        try {
            BeanMapper.copyProperty(oldBean, newBean);
        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
