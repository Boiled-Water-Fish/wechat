package com.cherlshall.wechat.entity.msg.strategy;

import com.cherlshall.wechat.entity.mysql.UserInfo;
import com.cherlshall.wechat.mapper.AdminInfoMapper;
import com.cherlshall.wechat.mapper.MeetInfoMapper;
import com.cherlshall.wechat.mapper.SignInfoMapper;
import com.cherlshall.wechat.mapper.UserInfoMapper;
import com.cherlshall.wechat.util.sql.MapperUtil;
import com.cherlshall.wechat.util.wechat.SendUtil;
import com.cherlshall.wechat.util.wechat.WeChatConstant;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * 文字消息
 */
public class TextMsgStrategy implements MsgStrategy {
    private final MapperUtil mapperUtil = MapperUtil.getInstance();
    private final UserInfoMapper userInfoMapper = mapperUtil.getUserInfoMapper();
    private final MeetInfoMapper meetInfoMapper = mapperUtil.getMeetInfoMapper();
    private final AdminInfoMapper adminInfoMapper = mapperUtil.getAdminInfoMapper();
    private final SignInfoMapper signInfoMapper = mapperUtil.getSignInfoMapper();

    @Override
    public String execute(Map<String, String> requestMap) {
        String content = getContent(requestMap);
        return SendUtil.sendTextMsg(requestMap, content);
    }

    private String getContent(Map<String, String> requestMap) {
        String userContent = requestMap.get(WeChatConstant.CONTENT);
        String adminName = adminInfoMapper.getAdminNameById(requestMap.get(WeChatConstant.FROM_USER_NAME));
        if (userContent.contains("+")) {
            if (userContent.split("\\+").length == 2) {
                if (userContent.split("\\+")[0].equals("签到")) {
                    return signInfo(requestMap);
                } else {
                    if (adminName == null) {
                        return "您还不是管理员，无法进行操作，请联系管理员添加您";
                    } else {
                        if (userContent.split("\\+")[0].equals("添加管理员")) {
                            return createAdmin(requestMap, adminName);
                        }
                    }
                }
            } else if (userContent.split("\\+").length == 3) {
                if (userContent.split("\\+")[0].equals("添加会议")) {
                    return createMeet(requestMap);

                }
            }
        }
        return "请告诉我你的姓名和您要参加的会议名称，格式如下：\"签到+你要参加会议的名称\"";
    }

    private String signInfo(Map<String, String> requestMap) {
        String userId = requestMap.get(WeChatConstant.FROM_USER_NAME);
        String userContent = requestMap.get(WeChatConstant.CONTENT);
        String meetName = userContent.split("\\+")[1];
        String userName = userInfoMapper.getUserNameById(requestMap.get(WeChatConstant.FROM_USER_NAME));
        if (userName == null) {
            return "还没有您的信息,请上传您的身份证正面照片，登记您的信息";
        } else {
            String meetInfo = meetInfoMapper.getMeetName(meetName);
            if (meetInfo == null) {
                return "没有找到您要参加的会议，请重新输入会议名称，或联系管理员确认会议，";
            } else {
                long signTime = System.currentTimeMillis() / 1000;
                int isOut = 0;
                long outTime = 0;
                signInfoMapper.inserSignInfo(userId, userName, signTime, isOut, outTime);
                return userName + "您好，欢迎您" + "参加" + meetName;
            }
        }
    }

    private String createAdmin(Map<String, String> requestMap, String adminName) {
        String userContent = requestMap.get(WeChatConstant.CONTENT);
        String addUserName = userContent.split("\\+")[1];
        String getAdminName = adminInfoMapper.getAdminNameByName(addUserName);
        if (getAdminName != null) {
            return "该用户已是管理员，无需重复添加";
        }
        UserInfo userInfo = userInfoMapper.getUserNameByName(addUserName);
        if (userInfo == null) {
            return "没有找到您要添加的用户，请该用户先注册再进行添加";
        } else {
            long updateTime = System.currentTimeMillis() / 1000;
            adminInfoMapper.insertAdminInfo(userInfo.getUserName(), userInfo.getIdCard(), userInfo.getUserId(), updateTime, adminName, 0);
            return "添加成功";
        }
    }

    private String createMeet(Map<String, String> requestMap) {
        String userContent = requestMap.get(WeChatConstant.CONTENT);
        String userName = userInfoMapper.getUserNameById(requestMap.get(WeChatConstant.FROM_USER_NAME));

        String meetName = userContent.split("\\+")[1];
        String meetTime = userContent.split("\\+")[2];
        if (meetName == null || meetTime == null || meetName.equals("") || meetTime.equals("")) {
            return "请输入正确的会议信息,格式如下“添加会议+会议名称+会议时间（例如：2008-08-08 08:08:08）”";
        }
        meetTime = date2TimeStamp(meetTime, "yyyy-MM-dd HH:mm:ss");
        if (meetTime.equals("")) {
            return "会议日期格式不对哦，日期格式类型如下“2008-08-08 08:08:08”，注意空格哦";
        }
        long createTime = System.currentTimeMillis() / 1000;
        meetInfoMapper.insertAdminInfo(meetName, createTime, 0, userName, 0);
        return "添加成功";


    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
