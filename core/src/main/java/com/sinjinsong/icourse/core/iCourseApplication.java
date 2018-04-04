package com.sinjinsong.icourse.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author sinjinsong
 * @date 2018/4/4
 */
@SpringBootApplication
@EnableConfigurationProperties
@Slf4j
public class iCourseApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(iCourseApplication.class, args);
        synchronized (iCourseApplication.class) {
            while (true) {
                try {
                    iCourseApplication.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }
}

