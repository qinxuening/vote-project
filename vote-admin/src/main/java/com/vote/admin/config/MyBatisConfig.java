package com.vote.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author qinxuening
 * @date 2022/9/13 21:05
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.vote.**.mapper","com.vote.admin.dao"})
public class MyBatisConfig {
}
