package com.ssm.test.shiromybatis.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ASUS
 */
@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    private Integer id;

    private String roleName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false,length = 20,columnDefinition = "timestamp default current_timestamp")
    private Date createTime = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,length = 20,columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updateTime = new Date();
    /**
     * 删除操作修改该字段 1有效 2无效
     */
    @Column(columnDefinition = "varchar(1) default '1'")
    private String deleteStatus;
}
