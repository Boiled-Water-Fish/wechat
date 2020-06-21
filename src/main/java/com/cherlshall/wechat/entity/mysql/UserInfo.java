package com.cherlshall.wechat.entity.mysql;

import lombok.Data;

@Data
public class UserInfo {
    private Integer id;
    private String userName;
    private String idCard;
    private String userId;
    private Integer updateTime;
}
