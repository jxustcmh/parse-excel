package com.hx.parse.excel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jxlgcmh
 * @date 2019-10-20 19:11
 */
@SpringBootApplication
@MapperScan("com.hx.parse.excel.mapper")
public class ParseExcelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParseExcelApplication.class,args);
    }
}
