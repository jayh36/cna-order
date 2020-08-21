package shop;

import shop.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{ //비즈니스 로직이 들어가는 부분
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    OrderRepository orderRepository ;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShipped_UpdateStatus(@Payload Shipped shipped) {

        if (shipped.isMe()) { //isMe() 라는 메서드는 -> 원래라면 저 if 문이 if( 이벤트.getEventType().equals(“이벤트클레스명”) 이런식으로 조건문이 완성되어야 하지만, 그걸 미리 정의해놓은 부분

            // 배송상태에 따른 주문서비스의 배송 상태값 없데이트.
            Optional<Order> orderOptional = orderRepository.findById(shipped.getOrderId());
            Order order = orderOptional.get();
            order.setStatus(shipped.getStatus());
            orderRepository.save(order); //findById->save 는 없데이트

            System.out.println("##### listener UpdateStatus : " + shipped.toJson());
        }

    }
}
