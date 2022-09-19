package com.vote.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 配置mybatis plus 自动填充
 *
 * @author qinxuening
 * @date 2022/9/13 18:01
 */
@Component
public class BaseMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        for (String str : metaObject.getGetterNames()) {
            if ("addTime".equals(str)) {
                this.setFieldValByName("addTime", new Date(), metaObject);
            }
            if ("createTime".equals(str)) {
                this.setFieldValByName("createTime", new Date(), metaObject);
            }
            if ("updateTime".equals(str)) {
                this.setFieldValByName("updateTime", new Date(), metaObject);
            }
            if ("voteTime".equals(str)) {
                this.setFieldValByName("voteTime", new Date(), metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        for (String str : metaObject.getGetterNames()) {
            if ("updateTime".equals(str)) {
                this.setFieldValByName("updateTime", new Date(), metaObject);
            }
        }
    }
}
