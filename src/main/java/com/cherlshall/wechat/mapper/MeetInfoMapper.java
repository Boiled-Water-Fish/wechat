package com.cherlshall.wechat.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MeetInfoMapper {
    @Select("select meet_name from meet_info where meet_name = #{meetName}")
    String getMeetName(@Param("meetName") String meetName);
    @Insert("insert into meet_info (meet_name,create_time,update_time,create_user,is_deleted) values (#{adminName},#{createTime},#{updateTime},#{createUser},#{isDeleted})")
    void insertAdminInfo(@Param("meetName") String meetName,@Param("createTime") long createTime, @Param("updateTime") long updateTime, @Param("createUser") String create_user, @Param("isDeleted") int isDeleted);


}
