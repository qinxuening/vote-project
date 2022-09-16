package com.vote.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 */
@SpringBootApplication(scanBasePackages = "com.vote")
@EnableAsync
public class VoteAdminApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(VoteAdminApplication.class, args);
    }
}
