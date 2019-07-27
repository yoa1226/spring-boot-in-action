package com.annu.action.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    @ApiModelProperty("用户id 唯一标识")
    private String Id;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户手机号")
    private String telephone;

}
