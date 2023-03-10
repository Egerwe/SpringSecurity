package com.Yao.domain;

import com.Yao.commonSensitive.mySensitive.CommonSensitive;
import com.Yao.commonSensitive.mySensitive.MyEmailSensitive;
import com.Yao.commonSensitive.myStrategy.MyCustomizeNameStrategy;
import com.Yao.handler.MD5TypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Author Yjw
 * 2023/1/24 14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = -40356785423868312L;
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    @CommonSensitive(strategy = MyCustomizeNameStrategy.class)
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    //@FieldEncrypt(algorithm = Algorithm.MD5_32)
    @TableField(typeHandler = MD5TypeHandler.class)
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    @MyEmailSensitive
    private String email;
    /**
     * 手机号
     */
    @CommonSensitive
    private String phonenumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型（0管理员，1普通用户）
     */
    private String userType;
    /**
     * 创建人的用户id
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
}
