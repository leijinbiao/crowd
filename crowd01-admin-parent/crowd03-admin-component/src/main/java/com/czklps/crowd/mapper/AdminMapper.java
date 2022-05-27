package com.czklps.crowd.mapper;

import com.czklps.crowd.entity.Admin;
import com.czklps.crowd.entity.AdminExample;
import java.util.List;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectByKeyword(String keyword);

    void deleteOldRelationship(Integer adminId);

    void insertNewRelationship(@Param("adminId") Integer adminId,@Param("roleIdList") List<Integer> roleIdList);
}