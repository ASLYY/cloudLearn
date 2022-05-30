package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

//@restcontroller注解包含@controller注解里面有注册进入spring容器的这个注解

@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListener {

    @Value("${server.port}")
    private String serverPort;

    //@StreamListener 监听的方法返回类型必须是void

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者1号，------->接收到的消息：" + message.getPayload()+"\t port: "+serverPort);
    }
}
