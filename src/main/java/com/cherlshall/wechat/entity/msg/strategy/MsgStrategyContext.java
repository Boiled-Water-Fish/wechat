package com.cherlshall.wechat.entity.msg.strategy;

import java.util.Map;

public class MsgStrategyContext {

    private final MsgStrategy msgStrategy;

    public MsgStrategyContext(MsgStrategy msgStrategy) {
        this.msgStrategy = msgStrategy;
    }

    public String execute(Map<String,String> requestMap) {
        // before
        // TODO
        // execute
        // after
        // TODO
        return this.msgStrategy.execute(requestMap);
    }
}
