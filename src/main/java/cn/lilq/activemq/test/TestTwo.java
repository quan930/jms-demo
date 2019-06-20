package cn.lilq.activemq.test;

import cn.lilq.activemq.util.Factory;

import javax.jms.JMSException;

public class TestTwo {
    public static void main(String[] args) throws JMSException {
        Factory.topicMessageProducer("1");
    }
}
