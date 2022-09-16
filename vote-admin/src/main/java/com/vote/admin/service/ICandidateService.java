package com.vote.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vote.admin.dto.CandidateParam;
import com.vote.entity.Candidate;

/**
 * 候选人 service
 * @author qinxuening
 * @date 2022/9/13 16:32
 */
public interface ICandidateService extends IService<Candidate> {
    /**
     * 添加候选人
     * @param candidateParam
     */
    void addCandidate(CandidateParam candidateParam);
}
