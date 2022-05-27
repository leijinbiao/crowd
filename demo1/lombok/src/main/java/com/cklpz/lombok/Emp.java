package com.cklpz.lombok;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Emp {

    private Integer id;
    private String empName;
    private String money;


}
