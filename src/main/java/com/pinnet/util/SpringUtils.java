package com.pinnet.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        this.beanFactory = configurableListableBeanFactory;
    }

    public static Object getBean(String name) {
        return beanFactory.getBean(name);
    }
    public static <T> T getBean(String name, Class<T> clazz) {
        return beanFactory.getBean(name,clazz);
    }
    public static <T> T getBean(Class<T> clazz) {
        return beanFactory.getBean(clazz);
    }
}
