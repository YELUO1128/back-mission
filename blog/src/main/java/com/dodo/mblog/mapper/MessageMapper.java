package com.dodo.mblog.mapper;

import com.dodo.mblog.entity.Message;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/5/29 13:11
 * @Description:
 */
public interface MessageMapper {

    public Message findMsgById(Integer msgid);
    public List<Message> findAllMsg();
    public void insertMsg();

    public void insertMsg(Message message);

    public void deleteById(Integer msgid);


}
