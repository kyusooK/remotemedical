package remotemedical.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import remotemedical.HospitalApplication;
import remotemedical.domain.ConsultationDataRegistered;
import remotemedical.domain.ConsultationDataUpdated;

@Entity
@Table(name = "Consultation_table")
@Data
//<<< DDD / Aggregate Root
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String patientName;

    private String patientSymptoms;

    private String pastHistory;

    private Date reservationDate;

    private String consultationContent;

    private String videoId;

    @Enumerated(EnumType.STRING)
    private ConsultationStatus consultationStatus;

    public static ConsultationRepository repository() {
        ConsultationRepository consultationRepository = HospitalApplication.applicationContext.getBean(
            ConsultationRepository.class
        );
        return consultationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void registerConsultationData(
        ConsultationReceived consultationReceived
    ) {

        Consultation consultation = new Consultation();
        consultation.setPatientName(consultationReceived.getUserName());
        consultation.setPatientSymptoms(consultationReceived.getSymptoms());
        consultation.setPastHistory(consultationReceived.getPastHistory());
        consultation.setReservationDate(consultationReceived.getReservationDate());
        consultation.setConsultationStatus(ConsultationStatus.RESERVED);
        repository().save(consultation);

        ConsultationDataRegistered consultationDataRegistered = new ConsultationDataRegistered(consultation);
        consultationDataRegistered.publishAfterCommit();

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateConsultationData(
        ReservationUpdated reservationUpdated
    ) {
        repository().findById(reservationUpdated.getId()).ifPresent(consultation->{
            
            consultation.setReservationDate(reservationUpdated.getReservationDate());
            consultation.setPastHistory(reservationUpdated.getPastHistory());
            consultation.setPatientSymptoms(reservationUpdated.getSymptoms());
            repository().save(consultation);

            ConsultationDataUpdated consultationDataUpdated = new ConsultationDataUpdated(consultation);
            consultationDataUpdated.publishAfterCommit();

         });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateConsultationData(
        ReservationCanceled reservationCanceled
    ) {
        repository().findById(reservationCanceled.getId()).ifPresent(consultation->{
            
            consultation.setConsultationStatus(ConsultationStatus.CANCELED);

            repository().save(consultation);

            ConsultationDataUpdated consultationDataUpdated = new ConsultationDataUpdated(consultation);
            consultationDataUpdated.publishAfterCommit();

         });

    }
    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public void progressVideoconsultation(
        ProgressVideoconsultationCommand progressVideoconsultationCommand
    ) {
        //implement business logic here:

        VideoConsultationProgressed videoConsultationProgressed = new VideoConsultationProgressed(
            this
        );
        videoConsultationProgressed.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void recordConsultation(
        RecordConsultationCommand recordConsultationCommand
    ) {
        repository().findById(recordConsultationCommand.getId()).ifPresent(consultation ->{

            consultation.setConsultationContent(recordConsultationCommand.getConsultationContent());

            repository().save(consultation);

            ConsultationRecorded consultationRecorded = new ConsultationRecorded(this);
            consultationRecorded.publishAfterCommit();
        });

    }

    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
