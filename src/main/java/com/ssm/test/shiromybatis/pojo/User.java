package com.ssm.test.shiromybatis.pojo;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ASUS
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String nickname;
    private Integer roleId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,nullable = false,length = 20)
    private Date createTime = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,length = 20)
    private Date updateTime = new Date();

    /**
     * 删除操作就是改变这个状态 1有效 2无效
     * 默认为1
     */
    @Column(nullable = false, columnDefinition = "varchar(1) default '1'")
    private String deleteStatus;

}
