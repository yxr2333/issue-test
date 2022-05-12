package com.ssssheep.test.work4.config;

import cn.dev33.satoken.stp.StpInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Intellij IDEA
 *
 * @author Xinrui Yu
 * @date 2022/4/10 15:14 星期日
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface{
    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {
        List<String> roles = new ArrayList<>();
        roles.add("super_admin");
        return roles;
    }
}
