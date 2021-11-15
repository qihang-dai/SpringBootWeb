package com.example.studentmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper {

        @Select("Select p.name from permission p innerjoin" +
                "role_permission rp" +
                "on rp.permission_id = p.id" +
                "where rp.role_id = #{role.id}")
        Set<String> getPermissionRoleId(@Param("role_id") Long roleId);
}
