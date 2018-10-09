package com.testdev.platform;

import com.testdev.platform.dao.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.testdev.platform.services","com.testdev.platform.domain","com.testdev.platform.config"})
public class PlatformApplication  {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}

//   需要用war包部署时的启动类
//    public class PlatformApplication  extends SpringBootServletInitializer
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(PlatformApplication.class);
//    }