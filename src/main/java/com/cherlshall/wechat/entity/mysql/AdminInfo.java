package com.cherlshall.wechat.entity.mysql;

import lombok.Data;

@Data
public class AdminInfo {
    private Integer id;
    private String adminName;
    private String idCard;
    private String adminId;
    private Integer updateTime;
    private String createUser;
    private Integer isDeleted;
}
