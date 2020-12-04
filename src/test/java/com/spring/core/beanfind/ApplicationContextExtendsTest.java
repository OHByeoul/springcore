package com.spring.core.beanfind;

import com.spring.core.discount.DiscountPolicy;
import com.spring.core.discount.FixDiscountPolicy;
import com.spring.core.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입 조회시 자식 여러개면 중복에러")
    void findBeanParentType(){
        DiscountPolicy bean = applicationContext.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, ()->applicationContext.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("자식 여러개면 빈이름으로 조회")
    void findBeanName(){
        DiscountPolicy rateDiscountPolicy = applicationContext.getBean("rateDiscountPolicy", DiscountPolicy.class);
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
