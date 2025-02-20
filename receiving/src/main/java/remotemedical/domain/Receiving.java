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

    private String message;

    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    @PostPersist
    public void onPostPersist() {

        remotemedical.external.Reservation reservation = new remotemedical.external.Reservation();
        
        reservation.setTaskId(this.getId().toString());
        reservation.setTitle("예약 완료");
        reservation.setDescription(
        "예약이 완료 되었습니다 예약날짜: " + this.getReservationDate().toString() + " 증상: " + this.getSymptoms());
        reservation.setNow(true);

        ReceivingApplication.applicationContext
            .getBean(remotemedical.external.ReservationService.class)
            .createReservation(reservation);

        ConsultationReceived consultationReceived = new ConsultationReceived(this);
        consultationReceived.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        repository().findById(this.getId()).ifPresent(receiving ->{

            remotemedical.external.Reservation reservation = new remotemedical.external.Reservation();
            reservation.setTaskId(this.getId().toString());
            reservation.setTitle("예약 완료");
            reservation.setDescription(
            "예약이 변경 되었습니다 예약날짜: " + receiving.getReservationDate().toString() + " 증상: " + receiving.getSymptoms());
            reservation.setNow(true);

            receiving.setReservationType(ReservationType.CANCELED);

            ReceivingApplication.applicationContext
                .getBean(remotemedical.external.ReservationService.class)
                .createReservation(reservation);

            ReservationUpdated reservationUpdated = new ReservationUpdated(this);
            reservationUpdated.publishAfterCommit();
        });
    }

    @PreUpdate
    public void onPreUpdate() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        repository().findById(this.getId()).ifPresent(receiving ->{

            remotemedical.external.Reservation reservation = new remotemedical.external.Reservation();
            reservation.setTaskId(this.getId().toString());
            reservation.setTitle("예약 취소");
            reservation.setDescription("예약이 취소 되었습니다.");
            reservation.setNow(true);

            ReceivingApplication.applicationContext
                .getBean(remotemedical.external.ReservationService.class)
                .createReservation(reservation);

            ReservationCanceled reservationCanceled = new ReservationCanceled(this);
            reservationCanceled.publishAfterCommit();
        });
    }

    public static ReceivingRepository repository() {
        ReceivingRepository receivingRepository = ReceivingApplication.applicationContext.getBean(
            ReceivingRepository.class
        );
        return receivingRepository;
    }

    //<<< Clean Arch / Port Method
    public static void notifyReservation(
        ReservationCreated reservationCreated) {}
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
