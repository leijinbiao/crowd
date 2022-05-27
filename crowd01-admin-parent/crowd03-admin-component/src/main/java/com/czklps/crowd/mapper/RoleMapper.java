package com.czklps.crowd.mapper;

import java.util.List;

import com.czklps.crowd.entity.Role;
import com.czklps.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByKeyword(String keyword);


    List<Role> selectAssignedRole(Integer adminId);

    List<Role> selectUnAssignRole(Integer adminId);
}