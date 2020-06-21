package com.cherlshall.wechat.util.sql;

import com.cherlshall.wechat.mapper.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class MapperUtil {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NpcMapper npcMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private MeetInfoMapper meetInfoMapper;
    @Autowired
    private AdminInfoMapper adminInfoMapper;
    @Autowired
    private SignInfoMapper signInfoMapper;

    private static MapperUtil mapperUtil;

    @PostConstruct
    public void init() {
        mapperUtil = this;
    }

    public static MapperUtil getInstance() {
        return mapperUtil;
    }

    public UserMapper getUserMapper() {
        return this.userMapper;
    }

    public NpcMapper getNpcMapper() {
        return this.npcMapper;
    }

    public UserInfoMapper getUserInfoMapper() {
        return this.userInfoMapper;
    }

}
