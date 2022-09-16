package com.vote.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 投票portal启动类
 *
 */
@SpringBootApplication(scanBasePackages = "com.vote")
public class VotePortalApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(VotePortalApplication.class, args);
    }
}
