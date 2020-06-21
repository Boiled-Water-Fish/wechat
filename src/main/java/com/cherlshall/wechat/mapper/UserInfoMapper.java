package com.cherlshall.wechat.mapper;

import com.cherlshall.wechat.entity.mysql.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoMapper {

    @Insert("insert into user_info (user_name,id_card,user_id,update_time) values (#{userName},#{idCard},#{userId},#{updateTime})")
    void insertUserInfo(@Param("userName") String userName, @Param("idCard") String idCard, @Param("userId") String userId, @Param("updateTime") long updateTime);

    @Select("select user_name from user_info where user_id = #{userId}")
    String getUserNameById(@Param("userId") String userId);

    @Select("select * from user_info where user_name = #{userName}")
    UserInfo getUserNameByName(@Param("userName") String userName);

    @Update("update user_info set user_name = #{userName},id_card = #{idCard},update_time = #{updateTime} where user_id = #{userId}")
    void updateUserInfo(@Param("userName") String userName, @Param("idCard") String idCard, @Param("userId") String userId, @Param("updateTime") long updateTime);

}
