package com.czklps.crowd.api;

import com.czklps.crowd.entity.po.MemberPO;
import com.czklps.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "crowd-mysql-provider")
public interface MySQLRemoteService {
    @RequestMapping("/get/memberpo/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);
}
