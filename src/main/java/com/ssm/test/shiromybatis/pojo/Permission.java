package com.ssm.test.shiromybatis.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ASUS
 */

@Data
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    private Integer id;

    private String permissionCode;

    private String permissionName;

    private String menuCode;
    private String menuName;

    private int requiredPermission;

}
