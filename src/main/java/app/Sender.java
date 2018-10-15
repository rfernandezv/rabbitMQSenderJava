package app;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Sender {
        private static final String QUEUE_NAME = "eventsJava";
    
	public static void main(String[] args) throws Exception {
            String url = System.getenv().get("RABBITMQ_URL");                

            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri(url);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World RFV!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();
	}
}