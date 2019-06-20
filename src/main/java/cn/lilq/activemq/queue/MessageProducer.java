package cn.lilq.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageProducer {
    //定义ActivMQ的连接地址
    private static final String ACTIVEMQ_URL = "tcp://lilq.cn:61616";

    private static final String QUEUE_NAME = "TestMessage";
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个目标
        Destination destination = session.createQueue(QUEUE_NAME);
        //创建一个生产者
        javax.jms.MessageProducer producer = session.createProducer(destination);
        //创建模拟10个消息
        for (int i = 1 ; i <= 10 ; i++){
            //创建消息
            TextMessage message = session.createTextMessage("我发送message:" + i);
            //发送消息
            producer.send(message);
            //在本地打印消息
            System.out.println("我现在发的消息是：" + message.getText());
        }
        //关闭连接
        connection.close();
    }
}
