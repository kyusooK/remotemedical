package remotemedical.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import remotemedical.config.kafka.KafkaProcessor;
import remotemedical.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ReceivingRepository receivingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationCreated'"
    )
    public void wheneverReservationCreated_NotifyReservation(
        @Payload ReservationCreated reservationCreated
    ) {
        ReservationCreated event = reservationCreated;
        System.out.println(
            "\n\n##### listener NotifyReservation : " +
            reservationCreated +
            "\n\n"
        );

        // Sample Logic //
        Receiving.notifyReservation(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
