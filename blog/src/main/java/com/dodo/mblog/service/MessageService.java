package com.dodo.mblog.service;

import com.dodo.mblog.entity.Message;
import com.dodo.mblog.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



@Service
public class MessageService{

    @Resource
    private MessageMapper messageMapper;


    public Message findMsgById(Integer msgid) {
        return messageMapper.findMsgById(msgid);
    }

    public List<Message> findAllMsg() {
        return messageMapper.findAllMsg();
    }

    public void insertMsg(Message message) {
        messageMapper.insertMsg(message);
    }

    public void deleteById(Integer msgid) {
        messageMapper.deleteById(msgid);
    }
}
