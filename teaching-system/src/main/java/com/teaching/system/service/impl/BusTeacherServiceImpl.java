package com.teaching.system.service.impl;

import com.teaching.common.core.domain.entity.SysUser;
import com.teaching.system.mapper.BusTeacherMapper;
import com.teaching.system.service.IBusTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author qiaoting
 * @date 2025-01-19
 */
@Service
public class BusTeacherServiceImpl implements IBusTeacherService
{
    @Autowired
    private BusTeacherMapper busTeacherMapper;

    @Override
    public List<SysUser> selectBusTeacheList(String[] roles) {
        return busTeacherMapper.selectUserByRole(roles);
    }
}
