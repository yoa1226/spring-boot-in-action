package com.annu.action.web;

import com.annu.action.annotation.Token;
import com.annu.action.vo.ResponseVo;
import com.annu.action.vo.UserVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("users")
@Api(value = "用户操作接口")
public class UserController {

    @Token
    @GetMapping("one")
    public ResponseVo<UserVo> findOne(@ApiIgnore @RequestParam(required = false) UserVo userVo) {
        return ResponseVo.success(userVo);
    }

}
