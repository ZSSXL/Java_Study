package com.zss.jwt.controller;

import com.auth0.jwt.interfaces.Claim;
import com.zss.jwt.common.ServerResponse;
import com.zss.jwt.config.JwtConfig;
import com.zss.jwt.entity.Manager;
import com.zss.jwt.entity.vo.LoginVo;
import com.zss.jwt.service.ManagerService;
import com.zss.jwt.util.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/8 15:50
 * @desc Manager - 控制器
 */
@Slf4j
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;
    private final JwtConfig jwtConfig;

    @Autowired
    public ManagerController(ManagerService managerService, JwtConfig jwtConfig) {
        this.managerService = managerService;
        this.jwtConfig = jwtConfig;

    }

    /**
     * 添加管理员
     *
     * @param manager 管理员实体
     * @return String
     */
    @PostMapping
    public ServerResponse<String> addManager(@RequestBody Manager manager) {
        managerService.addManagerService(manager);
        return ServerResponse.createBySuccess("添加成功");
    }

    /**
     * 管理员登录
     *
     * @param loginVo 登录实体
     * @return String
     */
    @PostMapping("/login")
    public ServerResponse<String> managerLogin(@RequestBody LoginVo loginVo) {
        Manager manager = managerService.login(loginVo.getUsername(), loginVo.getPassword());
        if (manager == null) {
            return ServerResponse.createByErrorMessage("登录失败");
        } else {
            String token = jwtConfig.createToken(MapUtil.create(
                    "username", manager.getManagerName(),
                    "password", manager.getManagerPassword()
            ));
            return ServerResponse.createBySuccessMessage(token);
        }
    }

    /**
     * 校验Token
     *
     * @param token 用户token
     * @return Map<String, String>
     */
    @GetMapping
    public ServerResponse<Map<String, String>> tokenTest(@RequestHeader("token") String token) {
        boolean valid = jwtConfig.isValid(token);
        String username = jwtConfig.getClaim(token, "username").asString();
        String password = jwtConfig.getClaim(token, "password").asString();
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("valid", "" + valid);
        return ServerResponse.createBySuccess(map);
    }
}
