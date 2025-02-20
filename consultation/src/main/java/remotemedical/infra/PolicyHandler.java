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
    ConsultationRepository consultationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConsultationReceived'"
    )
    public void wheneverConsultationReceived_RegisterConsultationData(
        @Payload ConsultationReceived consultationReceived
    ) {
        ConsultationReceived event = consultationReceived;
        System.out.println(
            "\n\n##### listener RegisterConsultationData : " +
            consultationReceived +
            "\n\n"
        );

        // Sample Logic //
        Consultation.registerConsultationData(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationUpdated'"
    )
    public void wheneverReservationUpdated_UpdateConsultationData(
        @Payload ReservationUpdated reservationUpdated
    ) {
        ReservationUpdated event = reservationUpdated;
        System.out.println(
            "\n\n##### listener UpdateConsultationData : " +
            reservationUpdated +
            "\n\n"
        );

        // Sample Logic //
        Consultation.updateConsultationData(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationCanceled'"
    )
    public void wheneverReservationCanceled_UpdateConsultationData(
        @Payload ReservationCanceled reservationCanceled
    ) {
        ReservationCanceled event = reservationCanceled;
        System.out.println(
            "\n\n##### listener UpdateConsultationData : " +
            reservationCanceled +
            "\n\n"
        );

        // Sample Logic //
        Consultation.updateConsultationData(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
