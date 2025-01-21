package com.teaching.system.service;

import com.teaching.common.core.domain.entity.SysUser;

import java.util.List;

/**
 *
 * @author qiaoting
 * @date 2025-01-19
 */
public interface IBusTeacherService
{

    public List<SysUser> selectBusTeacheList(String[] roles);

}
