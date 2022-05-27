package com.czklps.crowd.service.api;

import com.czklps.crowd.entity.po.MemberPO;
import com.czklps.crowd.util.ResultEntity;

public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginacct);
}
