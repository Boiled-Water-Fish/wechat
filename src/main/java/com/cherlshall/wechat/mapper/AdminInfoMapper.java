package com.cherlshall.wechat.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminInfoMapper {
    @Select("select admin_name from admin_info where admin_id = #{adminId}")
    String getAdminNameById(@Param("adminId") String adminId);

    @Select("select admin_name from admin_info where admin_name = #{adminName}")
    String getAdminNameByName(@Param("adminName") String adminName);

    @Insert("insert into admin_info (admin_name,id_card,admin_id,update_time,create_username,is_deleted) values (#{adminName},#{idCard},#{adminId},#{updateTime},#{createUser},#{isDeleted})")
    void insertAdminInfo(@Param("adminName") String adminName, @Param("idCard") String idCard, @Param("adminId") String adminId, @Param("updateTime") long updateTime, @Param("createUser") String create_user, @Param("isDeleted") int isDeleted);

}
