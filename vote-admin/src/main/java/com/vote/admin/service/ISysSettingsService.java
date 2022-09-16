package com.vote.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vote.entity.SysSettings;

/**
 * 系统配置 service
 * @author qinxuening
 * @date 2022/9/13 16:59
 */
public interface ISysSettingsService extends IService<SysSettings> {
    /**
     * 设置选举开始或者结束
     * @param type
     */
    void startOrend(Integer type) throws InterruptedException;
}
