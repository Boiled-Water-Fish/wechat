package com.cherlshall.wechat.entity.mysql;

import lombok.Data;

@Data
public class SignInfo {
    private String user_name;
    private String user_id;
    private int sign_time;
    private int out_time;
    private int is_out;
}
