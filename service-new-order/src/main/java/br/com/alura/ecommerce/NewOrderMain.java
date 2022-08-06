package br.com.alura.ecommerce;

import br.com.alura.ecommerce.model.EmailDTO;
import br.com.alura.ecommerce.model.OrderDTO;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var orderKafkaDispatcher = new KafkaDispatcher<OrderDTO>()) {
            try (var emailKafkaDispatcher = new KafkaDispatcher<EmailDTO>()) {
                for (int i = 0; i < 10; i++) {
                    String userId = UUID.randomUUID().toString();
                    String orderId = UUID.randomUUID().toString();
                    BigDecimal amount = BigDecimal.valueOf(Math.random() * 5000 + 1);
                    OrderDTO order = new OrderDTO(userId, orderId, amount);
                    orderKafkaDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                    String email = "Thank you for your order! We are processing your order now!";
                    emailKafkaDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, new EmailDTO("Oder", email));
                }
            }
        }
    }

}

