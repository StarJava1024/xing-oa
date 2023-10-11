package com.xing;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
// @MapperScan("com.xing.*.mapper")  // 能找到 mapper 动态创建对象
@EnableTransactionManagement  // 开启事务
public class ServiceAutoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAutoApplication.class, args);
        log.info("xing-oa 系统启动成功！");
    }
}