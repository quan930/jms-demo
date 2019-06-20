package cn.lilq.activemq.test;

import cn.lilq.activemq.util.Factory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Test {
    public static void main(String[] args) throws JMSException {
//        Factory.queueMessageConsumer(new MessageListener() {
//            public void onMessage(Message message) {
//                TextMessage textMessage = (TextMessage)message;
//                try {
//                    System.out.println(textMessage.getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        Factory.topicMessageConsumer(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}