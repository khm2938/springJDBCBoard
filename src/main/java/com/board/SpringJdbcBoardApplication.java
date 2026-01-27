package com.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.board", "com.member"})
public class SpringJdbcBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcBoardApplication.class, args);
    }
}
