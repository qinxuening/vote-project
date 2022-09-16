package com.vote.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vote.admin.dto.CandidateParam;
import com.vote.admin.exception.AdminException;
import com.vote.admin.service.ICandidateService;
import com.vote.common.constant.Constants;
import com.vote.common.exception.GlobalAsserts;
import com.vote.common.service.IRedisCacheService;
import com.vote.entity.Candidate;
import com.vote.mapper.CandidateMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 候选人 service 实现类
 * @author qinxuening
 * @date 2022/9/13 17:02
 */
@Service
public class CandidateServiceImpl extends ServiceImpl<CandidateMapper, Candidate> implements ICandidateService {
    @Autowired
    private IRedisCacheService iRedisCacheService;

    @Override
    public void addCandidate(CandidateParam candidateParam) {
        Candidate candidateResult = this.getOne(Wrappers.<Candidate>lambdaQuery().eq(Candidate::getIdNumber, candidateParam.getIdNumber()));
        if (candidateResult == null) {
            Candidate candidate = new Candidate();
            BeanUtils.copyProperties(candidateParam, candidate);
            Integer id = baseMapper.insert(candidate);
            if ( id <= 0) GlobalAsserts.fail(AdminException.ADD_CANDIDATE_FAIL);
            // redis 初始化候选人得票情况，全部赋0值
            iRedisCacheService.set(Constants.CANDIDATE_COUNT_PRE + candidate.getId(), 0);
            // 添加候选人信息到redis中，方便后续快速查询
            iRedisCacheService.sAdd(Constants.CANDIDATE_INFO, candidate);
        } else {
            GlobalAsserts.fail(AdminException.EXIST_THIS_CANDIDATE);
        }
    }
}
