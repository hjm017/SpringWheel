/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.springwheel.common.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 简单封装orika, 实现深度转换Bean<->Bean的Mapper.
 */
public class BeanMapper {

    private static MapperFacade mapper        = null;
    private static MapperFactory mapperFactory = null;

    static {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();

    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    /**
     * 对象属性拷贝
     * @param oldBean
     * @param newBean
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void copyProperty(Object oldBean, Object newBean) throws NoSuchFieldException,
                                                                    IllegalAccessException {
        //新的class
        Class newClass = newBean.getClass();
        //老的class
        Class oldClass = oldBean.getClass();
        //该类所有的属性
        Field[] newFields = newClass.getDeclaredFields();
        //新的属性
        Field newField = null;
        //老的属性
        Field oldField = null;
        for (Field f : newFields) {
            //类中的属性名称
            String fieldName = f.getName();
            //通过属性名称获取属性
            newField = newClass.getDeclaredField(fieldName);
            //获取属性的值时需要设置为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
            //值为 false 则指示反射的对象应该实施 Java 语言访问检查。
            newField.setAccessible(true);
            //根据属性获取对象上的值
            Object newObject = newField.get(newBean);
            //过滤serialVersionUID的属性
            if ("serialVersionUID".equals(fieldName)) {
                continue;
            }
            //过滤空的属性或者一些默认值
            if (isContinue(newObject)) {
                continue;
            }
            oldField = oldClass.getDeclaredField(fieldName);
            oldField.setAccessible(true);
            oldField.set(oldBean, newObject);
        }
    }

    /**
     *  是否跳出本次循环
     * @author 2014-11-6 上午11:37:22
     * @param object
     * @return true 是 有null或者默认值
     *         false 否 有默认值
     */
    private static boolean isContinue(Object object) {
        if (object == null || "".equals(object)) {
            return true;
        }
        String valueStr = object.toString();
        if ("0".equals(valueStr) || "0.0".equals(valueStr)) {
            return true;
        }
        return false;
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<D> destinationClass) {
        return mapper.mapAsList(sourceList, destinationClass);
    }

    public static MapperFactory getMapperFactory() {
        return mapperFactory;
    }

}