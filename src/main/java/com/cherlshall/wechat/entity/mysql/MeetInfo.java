package com.cherlshall.wechat.entity.mysql;

import lombok.Data;

@Data
public class MeetInfo {
    private Integer id;
    private String meetRoom;
    private String createTime;
    private String createUser;
    private String updateTime;
    private String isDeleted;
    private String meetTime;
}
