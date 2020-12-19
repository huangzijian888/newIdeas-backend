package com.xslgy.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xslgy.common.entity.Partner;
import com.xslgy.common.repository.PartnerRepository;
import com.xslgy.common.service.PartnerService;
import com.xslgy.common.utils.RegexUtils;
import com.xslgy.core.exception.XSLGYException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lamdaer
 * @createTime 2020/12/15
 */
@Slf4j
@Service
public class PartnerServiceImpl implements PartnerService {
    @Resource
    PartnerRepository partnerRepository;
    
    @Override
    public Partner save(Partner partner) {
        String phoneNumber = partner.getPhoneNumber();
        String email = partner.getEmail();
        if (!RegexUtils.checkMobile(phoneNumber)) {
            log.error("【伙伴管理——Save】手机号不合法，输入参数为：" + phoneNumber);
            throw new XSLGYException("手机号不合法");
        }
        if (!RegexUtils.checkEmail(email)) {
            log.error("伙伴管理——Save】邮箱地址不合法，输入参数为：" + email);
            throw new XSLGYException("邮箱不合法");
        }
        return partnerRepository.save(partner);
    }
    
}