package com.example.testonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 陈少鑫
 *
 */

//
@SpringBootApplication
@MapperScan("com.example.testonline.mapper")
public class TestonlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestonlineApplication.class, args);
    }

}
