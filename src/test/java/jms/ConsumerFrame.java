package jms;

import com.jyhon.jms.PtpCosumer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConsumerFrame extends JFrame {
    private JTextField brokerUrlTf;
    private JTextField destinationUrlTf;
    private JTextArea msgContentTa;
    private JButton openButton;
    private JButton recButton;

    private Connection con;
    private Queue queue;
    private Session sen;
    private MessageConsumer reciever;

    PtpCosumer ptpCosumer = new PtpCosumer();

    public ConsumerFrame() {
        this.setSize(450, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("接收者");
        init();
        addEvent();
        this.setVisible(true);
    }

    private void init() {

       brokerUrlTf = new JTextField("failover://tcp://localhost:61616");
        destinationUrlTf = new JTextField("john_seq");
        msgContentTa = new JTextArea(5, 60);
        openButton = new JButton("打开");
        recButton = new JButton("接收");

        this.setLayout(null);
        brokerUrlTf.setBounds(10, 10, 400, 40);
        destinationUrlTf.setBounds(10, 60, 400, 40);

        msgContentTa.setBounds(10, 110, 400, 200);

        openButton = new JButton("打开");
        openButton.setBounds(10, 320, 90, 35);
        recButton.setBounds(150, 320, 90, 35);

        add(brokerUrlTf);
        add(destinationUrlTf);
        add(msgContentTa);
        add(openButton);
        add(recButton);
    }

    private void addEvent() {
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //open();

                try {
                    ptpCosumer.open();
                    ptpCosumer.startListenServer();
                } catch (JMSException e1) {
                    e1.printStackTrace();
                }
                ((JButton) e.getSource()).setEnabled(false);
            }
        });
        recButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String message = ptpCosumer.getOneMessage();
                    if (!message.isEmpty()){
                        msgContentTa.append(message + "\n");
                    }
                } catch (JMSException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(con);
                System.out.println("disconect....");
                try {
                    reciever.close();
                    sen.close();
                    con.close();
                } catch (JMSException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }

    private void open() {
        String brokerUrl = brokerUrlTf.getText().trim();
        String destinationUrl = destinationUrlTf.getText().trim();
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
            queue = new ActiveMQQueue(destinationUrl);
            con = factory.createConnection();
            sen = con.createSession(true, Session.CLIENT_ACKNOWLEDGE);
            reciever = sen.createConsumer(queue);
            con.start();
            System.out.println("connect: " + brokerUrl + "......'s " + destinationUrl);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ConsumerFrame mf = new ConsumerFrame();
    }
}
