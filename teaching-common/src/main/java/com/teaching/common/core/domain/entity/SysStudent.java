package com.teaching.common.core.domain.entity;

import com.teaching.common.annotation.Excel;
import com.teaching.common.annotation.Excel.ColumnType;
import com.teaching.common.annotation.Excel.Type;
import com.teaching.common.annotation.Excels;
import com.teaching.common.core.domain.BaseEntity;
import com.teaching.common.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 * 
 * @author qiaoting
 */
public class SysStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "部门编号", type = Type.IMPORT)
    private String deptId;

    @Excel(name = "登录名称", type = Type.IMPORT)
    private String userName;

    @Excel(name = "用户名称", type = Type.IMPORT)
    private String nickName;

    @Excel(name = "性别", type = Type.IMPORT, readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    @Excel(name = "手机号码", type = Type.IMPORT, cellType = ColumnType.TEXT)
    private String phonenumber;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
