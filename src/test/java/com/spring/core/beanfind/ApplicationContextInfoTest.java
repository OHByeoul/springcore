package com.spring.core.beanfind;

import com.spring.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

public class ApplicationContextInfoTest {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 전부 출력")
    void findAllBean(){
        String [] beanDefinitonNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitonName : beanDefinitonNames) {
            Object bean = applicationContext.getBean(beanDefinitonName);
            System.out.println("name = " + beanDefinitonName+"object = "+bean);
        }
    }

}
