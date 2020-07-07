package com.zss.postgres_demo.util;

import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 17:11
 * @desc dozer 转换器
 */
@Component
public class GeneralConverter {

    @Resource
    Mapper mapper;

    /**
     * List 实体类 转化器
     *
     * @param source 源数据
     * @param clazz  转化类型
     * @param <T>    泛型
     * @param <S>    泛型
     * @return List<T>
     */
    public <T, S> List<T> converter(List<S> source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(mapper.map(s, clazz));
        }
        return list;
    }

    /**
     * Set 实体类 深度转换器
     *
     * @param source 源数据
     * @param clazz  目标对象
     * @param <T>    泛型
     * @param <S>    泛型
     * @return Set<T>
     */
    public <T, S> Set<T> converter(Set<S> source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        Set<T> set = new TreeSet<>();
        for (S s : source) {
            set.add(mapper.map(s, clazz));
        }
        return set;
    }

    /**
     * 实体类 转换
     *
     * @param source 源数据
     * @param clazz  目标对象
     * @param <T>    泛型
     * @param <S>    泛型
     * @return T
     */
    public <T, S> T converter(S source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, clazz);
    }

    /**
     * 转换器
     *
     * @param source 源数据
     * @param object 目标对象
     */
    public void converter(Object source, Object object) {
        mapper.map(source, object);
    }

    /**
     * 转换器
     *
     * @param source 源数据
     * @param object 目标对象
     * @param <T>    泛型
     */
    public <T> void copyConverter(T source, Object object) {
        mapper.map(source, object);
    }

}
