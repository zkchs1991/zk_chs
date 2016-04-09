package com.github.apache.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by zk_chs on 16/4/9.
 */
public class Producer extends Thread{

    private final KafkaProducer producer;

    public Producer(){
        Properties props = new Properties();
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("bootstrap.servers", "localhost:9092");
        this.producer = new KafkaProducer(props);
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true) {
            String messageStr = "Message_" + messageNo;
            System.out.println("Send:" + messageStr);
            producer.send(new ProducerRecord("my_test", messageStr));
            messageNo++;
            try {
                sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
