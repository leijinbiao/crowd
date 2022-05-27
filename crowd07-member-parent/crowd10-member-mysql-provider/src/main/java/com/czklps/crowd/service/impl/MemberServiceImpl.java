package com.czklps.crowd.service.impl;

import com.czklps.crowd.entity.po.MemberPO;
import com.czklps.crowd.entity.po.MemberPOExample;
import com.czklps.crowd.mapper.MemberPOMapper;
import com.czklps.crowd.service.api.MemberService;
import com.czklps.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginacct) {

        MemberPOExample example = new MemberPOExample();

        MemberPOExample.Criteria criteria = example.createCriteria();

        criteria.andLoginacctEqualTo(loginacct);

        List<MemberPO> list = memberPOMapper.selectByExample(example);

        MemberPO memberPO = list.get(0);

        return memberPO;
    }
}
