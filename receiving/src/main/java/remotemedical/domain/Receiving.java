package remotemedical.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import remotemedical.ReceivingApplication;
import remotemedical.domain.ConsultationReceived;
import remotemedical.domain.ReservationCanceled;
import remotemedical.domain.ReservationUpdated;

@Entity
@Table(name = "Receiving_table")
@Data
//<<< DDD / Aggregate Root
public class Receiving {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String symptoms;

    private Date reservationDate;

    private String pastHistory;

    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        remotemedical.external.Reservation reservation = new remotemedical.external.Reservation();
        // mappings goes here
        ReceivingApplication.applicationContext
            .getBean(remotemedical.external.ReservationService.class)
            .createReservation(reservation);

        ConsultationReceived consultationReceived = new ConsultationReceived(
            this
        );
        consultationReceived.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        remotemedical.external.Reservation reservation = new remotemedical.external.Reservation();
        // mappings goes here
        ReceivingApplication.applicationContext
            .getBean(remotemedical.external.ReservationService.class)
            .createReservation(reservation);

        ReservationUpdated reservationUpdated = new ReservationUpdated(this);
        reservationUpdated.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        remotemedical.external.Reservation reservation = new remotemedical.external.Reservation();
        // mappings goes here
        ReceivingApplication.applicationContext
            .getBean(remotemedical.external.ReservationService.class)
            .createReservation(reservation);

        ReservationCanceled reservationCanceled = new ReservationCanceled(this);
        reservationCanceled.publishAfterCommit();
    }

    public static ReceivingRepository repository() {
        ReceivingRepository receivingRepository = ReceivingApplication.applicationContext.getBean(
            ReceivingRepository.class
        );
        return receivingRepository;
    }

    //<<< Clean Arch / Port Method
    public static void notifyReservation(
        ReservationCreated reservationCreated
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Receiving receiving = new Receiving();
        repository().save(receiving);

        */

        /** Example 2:  finding and process
        

        repository().findById(reservationCreated.get???()).ifPresent(receiving->{
            
            receiving // do something
            repository().save(receiving);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
