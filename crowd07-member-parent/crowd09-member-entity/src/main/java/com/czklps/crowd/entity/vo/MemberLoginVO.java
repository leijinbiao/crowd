package com.czklps.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberLoginVO {
    private Integer id;
    private String username;
    private String email;
}
