package cn.lilq.activemq.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ResourceBundle;

public class Factory {
    //定义ActivMQ的连接地址
    private static String ACTIVEMQ_URL = ResourceBundle.getBundle("config").getString("ACTIVEMQ_URL");
    //生产者消费者队列名称
    private static String QUEUE_NAME = ResourceBundle.getBundle("config").getString("QUEUE_NAME");
    //生产者消费者主题模式名称
    private static String TOPIC_NAME = ResourceBundle.getBundle("config").getString("TOPIC_NAME");

    /**
     * 队列模式生产者
     * @param message 需要发送的消息内容
     * @throws JMSException 异常
     */
    public static void queueMessageProducer(String message) throws JMSException {
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
        //创建消息
        TextMessage mess = session.createTextMessage(message );
        //发送消息
        producer.send(mess);
        //关闭连接
        connection.close();
    }

    /**
     * 队列模式消费者
     * @param messageListener 接收回调
     * @return Connection 用于关闭连接
     * @throws JMSException 异常
     */
    public static Connection queueMessageConsumer(MessageListener messageListener) throws JMSException {
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
        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //创建消费的监听
        consumer.setMessageListener(messageListener);
        //返回连接对象(用于关闭连接)
        return connection;
    }

    /**
     * 主题模式生产者
     * @param message 消费内容
     * @throws JMSException 异常
     */
    public static void topicMessageProducer(String message) throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个目标(主题模式)
        Destination destination = session.createTopic(TOPIC_NAME);
        //创建一个生产者
        javax.jms.MessageProducer producer = session.createProducer(destination);
        //创建消息
        TextMessage mess = session.createTextMessage(message);
        //发送消息
        producer.send(mess);
        //关闭连接
        connection.close();
    }

    /**
     * 主题模式消费者
     * @param messageListener 接收回调
     * @return Connection 用于关闭连接
     * @throws JMSException 异常
     */
    public static Connection topicMessageConsumer(MessageListener messageListener) throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个目标(主题模式)
        Destination destination = session.createTopic(TOPIC_NAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //创建消费的监听
        consumer.setMessageListener(messageListener);
        return connection;
    }
}
