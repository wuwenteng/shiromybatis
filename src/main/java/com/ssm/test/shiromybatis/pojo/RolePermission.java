package com.ssm.test.shiromybatis.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ASUS
 */
@Data
@Entity
@Table(name = "role_permission")
public class RolePermission {
    @Id
    @GeneratedValue
    private Integer id;

    private int roleId;

    private int permissionId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,nullable = false, length = 20)
    private Date createTime = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, length = 20)
    private Date updateTime = new Date();

    @Column(columnDefinition = "varchar(1) default 1")
    private String deleteStatus;
}
