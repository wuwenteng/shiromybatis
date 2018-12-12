package com.ssm.test.shiromybatis.util;

import com.ssm.test.shiromybatis.pojo.Permission;
import com.ssm.test.shiromybatis.pojo.User;

/**
 * @author ASUS
 *
 * 除id外全部字段都复制
 */
public class CopyUtil {

    public static User copyUser(User newOne, User oldOne) {
        oldOne.setDeleteStatus(newOne.getDeleteStatus());
        oldOne.setCreateTime(newOne.getCreateTime());
        oldOne.setNickname(newOne.getNickname());
        oldOne.setPassword(newOne.getPassword());
        oldOne.setRoleId(newOne.getRoleId());
        oldOne.setUpdateTime(newOne.getUpdateTime());
        oldOne.setUsername(newOne.getUsername());

        return oldOne;
    }

    public static Permission copyPermission(Permission newOne, Permission oldOne) {
        oldOne.setMenuCode(newOne.getMenuCode());
        oldOne.setMenuName(newOne.getMenuName());
        oldOne.setPermissionCode(newOne.getPermissionCode());
        oldOne.setPermissionName(newOne.getPermissionName());
        oldOne.setRequiredPermission(newOne.getRequiredPermission());

        return oldOne;
    }
}
