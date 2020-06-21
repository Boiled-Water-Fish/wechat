package com.cherlshall.wechat.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignInfoMapper {
    @Insert("insert into sign_info (user_id,user_name,sign_time,is_out,out_time) values(#{userId},#{userName},#{signTime},#{isOut},#{outTime})")
    void inserSignInfo(@Param("userId") String userId, @Param("userName") String userName, @Param("signTime") long signTime, @Param("isOut") int isOut, @Param("outTime") long outTime);
}
