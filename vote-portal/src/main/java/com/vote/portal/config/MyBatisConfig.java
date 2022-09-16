package com.vote.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author qinxuening
 * @date 2022/9/14 11:36
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.vote.**.mapper","com.vote.portal.dao"})
public class MyBatisConfig {
}
