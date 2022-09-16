package com.vote.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vote.admin.component.AsyncService;
import com.vote.admin.exception.AdminException;
import com.vote.admin.service.ISysSettingsService;
import com.vote.common.api.ResultCode;
import com.vote.common.constant.Constants;
import com.vote.common.exception.GlobalAsserts;
import com.vote.common.service.IRedisCacheService;
import com.vote.entity.SysSettings;
import com.vote.mapper.SysSettingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 系统配置 service 实现类
 *
 * @author qinxuening
 * @date 2022/9/13 17:05
 */
@Service
public class SysSettingsServiceImpl extends ServiceImpl<SysSettingsMapper, SysSettings> implements ISysSettingsService {
    @Autowired
    private IRedisCacheService iRedisCacheService;

    @Autowired
    private AsyncService asyncService;

    @Override
    public void startOrend(Integer type) throws InterruptedException {
        // 参数校验
        if (!Constants.DO_ELECTION_LIST.contains(type)) {
            GlobalAsserts.fail(ResultCode.VALIDATE_FAILED);
        }
        SysSettings result = this.getOne(Wrappers.<SysSettings>lambdaQuery()
                .eq(SysSettings::getGroupCode, Constants.VOTE)
                .eq(SysSettings::getFieldName, Constants.DO_ELECTION));
        if (result != null) {
            // 重新操作异常
            if (!StrUtil.isBlank(result.getFieldValue())) {
                if (Objects.equals(Integer.valueOf(result.getFieldValue()), Constants.VOTE_END)) {
                    GlobalAsserts.fail(AdminException.ELECTION_IS_OVER);
                } else if (Objects.equals(Integer.valueOf(result.getFieldValue()), Constants.VOTE_START) && Objects.equals(type, Constants.VOTE_START)) {
                    GlobalAsserts.fail(AdminException.ELECTION_IS_DOING);
                }
            }
            result.setFieldValue(String.valueOf(type));
            if (!this.updateById(result))
                GlobalAsserts.fail(ResultCode.FAILED);
            iRedisCacheService.del(Constants.DO_ELECTION_SYS_INFO);
            // 选举结束，邮件发送
            if (Objects.equals(Integer.valueOf(result.getFieldValue()), Constants.VOTE_END)) {
                // 异步执行邮件发送，使用线程池
                asyncService.sendEmail();
            }
        }
    }

}
