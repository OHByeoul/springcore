package com.spring.core.beanfind;

import com.spring.core.member.Member;
import com.spring.core.member.MemberRepository;
import com.spring.core.member.MemberServiceImpl;
import com.spring.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextDuplicateTest {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DuplicateConfig.class);
    @Test
    @DisplayName("빈 중복 에러 테스트")
    void duplicateBeanError(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
        ()-> applicationContext.getBean(MemberRepository.class));

    }

    @Configuration
    static  class DuplicateConfig{
        @Bean
        public MemberRepository memberRepository(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
