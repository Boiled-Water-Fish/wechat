package com.cherlshall.wechat.entity.msg.strategy;

import com.cherlshall.wechat.mapper.UserInfoMapper;
import com.cherlshall.wechat.util.sql.MapperUtil;
import com.cherlshall.wechat.util.wechat.IdCardUtil;
import com.cherlshall.wechat.util.wechat.SendUtil;
import com.cherlshall.wechat.util.wechat.WeChatConstant;

import java.util.Map;

/**
 * 图片消息
 */
public class ImageMsgStrategy implements MsgStrategy {
    private final MapperUtil mapperUtil = MapperUtil.getInstance();
    private final UserInfoMapper userInfoMapper = mapperUtil.getUserInfoMapper();

    @Override
    public String execute(Map<String, String> requestMap) {
        Map<String, String> userInfoMap = IdCardUtil.getUserInfo(requestMap.get(WeChatConstant.PIC_URL));
        String content = "";
        if (userInfoMap == null) {
            content = "您上传的照片无法识别哦，请上传正确清晰的身份证照片~";
        } else {
            String name = userInfoMap.get("userName");
            content = "请确认您的身份信息如下：\n姓名:" + name + "\n身份证号:" + userInfoMap.get("idCard") + "\n如有疑问，请重新上传照片更新身份";
            String userName = userInfoMapper.getUserNameById(requestMap.get(WeChatConstant.FROM_USER_NAME));
            long nowTime = System.currentTimeMillis()/1000;
            if (userName == null) {
                userInfoMapper.insertUserInfo(name, userInfoMap.get("idCard"), requestMap.get(WeChatConstant.FROM_USER_NAME), nowTime);
            } else {
                userInfoMapper.updateUserInfo(name, userInfoMap.get("idCard"), requestMap.get(WeChatConstant.FROM_USER_NAME), nowTime);
            }
        }
        return SendUtil.sendTextMsg(requestMap, content);
    }
}
