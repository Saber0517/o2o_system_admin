package com.jyhon.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import javax.jms.Queue;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class PtpCosumer {

    private static Map<String, String> configMap = new HashMap<String, String>();

    static {
        InputStream inputStream = PtpCosumer.class.getClassLoader().getResourceAsStream("jms.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
            for (Object keyName : p.keySet()) {
                System.out.println(p.get(keyName));
                configMap.put((String) keyName, (String) p.get(keyName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;
    private Queue queue;
    private Session session;
    private MessageConsumer consumer;
    private ArrayBlockingQueue<TextMessage> messageArrayBlockingQueue;

    public static Map<String, String> getConfigMap() {
        Map<String, String> mirrorMap = new HashMap<String, String>(configMap);
        return mirrorMap;
    }

    public void open() {
        String brokerUrl = configMap.get("serveraddress");
        String destinationUrl = configMap.get("queuename");
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
            queue = new ActiveMQQueue(destinationUrl);
            connection = factory.createConnection();
            session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
            connection.start();
            messageArrayBlockingQueue = new ArrayBlockingQueue<TextMessage>(1);
            System.out.println("connect: " + brokerUrl + "......'s " + destinationUrl);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void commitMessag() throws JMSException {
        messageArrayBlockingQueue.poll();
        session.commit();
    }

    //管理员查看一个信息
    public String getOneMessage() throws JMSException {
        TextMessage tmg = messageArrayBlockingQueue.peek();//peek not remove
        if (null == tmg) {
            return "";
        } else {
            return tmg.getText();
        }
    }

    //查看统计
    public String countAllMessage() {
        return "";
    }

    /*public int countUnacKnowLedged() {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" +
                RunServer.connectorPort + RunServer.connectorPath);
        JMXConnector connector = JMXConnectorFactory.connect(url, null);
        try {
            connector.connect();
            MBeanServerConnection connection = null;
            connection = connector.getMBeanServerConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 需要注意的是，这里的jms-broker必须和上面配置的名称相同
        ObjectName name = new ObjectName("jms-broker" + ":BrokerName=localhost,Type=Broker");
        BrokerViewMBean mBean = (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(connection,
                name, BrokerViewMBean.class, true);
        // System.out.println(mBean.getBrokerName());

        for (ObjectName queueName : mBean.getQueues()) {
            QueueViewMBean queueMBean = (QueueViewMBean) MBeanServerInvocationHandler
                    .newProxyInstance(connection, queueName, QueueViewMBean.class, true);
            System.out.println("\n------------------------------\n");
            // 消息队列名称
            System.out.println("States for queue --- " + queueMBean.getName());
            // 队列中剩余的消息数
            System.out.println("Size --- " + queueMBean.getQueueSize());
            // 消费者数
            System.out.println("Number of consumers --- " + queueMBean.getConsumerCount());
            // 出队数
            System.out.println("Number of dequeue ---" + queueMBean.getDequeueCount());
        }

        return 0;
    }*/

    public void startListenServer() throws JMSException {

        MessageConsumer consumer = session.createConsumer(queue);
      /*  consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message msg) {
                System.out.printf("receive new jms...");
                TextMessage tmsg = (TextMessage) msg;
                messageArrayBlockingQueue.add(tmsg);
                try {
                    session.commit();
                    session.close();
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });
        */
        Message msg = consumer.receive();
        TextMessage tmsg = (TextMessage) msg;
        if (null != tmsg) {
            System.out.printf(tmsg.getText());
        }
        session.commit();
        session.close();
        connection.close();
        messageArrayBlockingQueue.add(tmsg);

       /* while (true) {
            Message msg = consumer.receive();
            TextMessage tmsg = (TextMessage) msg;
            messageArrayBlockingQueue.add(tmsg);
        }*/
    }


}
