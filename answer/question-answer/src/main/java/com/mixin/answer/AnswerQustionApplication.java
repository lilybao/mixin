package com.mixin.answer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 答题快速启动类
 * @author: li baojian
 * @create: 2019/12/02 13:57
 */
@SpringBootApplication
@ComponentScan({"com.mixin.answer.*"})
public class AnswerQustionApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnswerQustionApplication.class, args);
    }
}
