package com.teaching.system.mapper;

import com.teaching.common.core.domain.entity.SysUser;

import java.util.List;

/**
 * 讲师Mapper接口
 * 
 * @author qiaoting
 * @date 2025-01-19
 */
public interface BusTeacherMapper
{

    public List<SysUser> selectUserByRole(String[] roles);

}
