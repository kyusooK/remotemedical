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
        //implement business logic here:

        ConsultationRecorded consultationRecorded = new ConsultationRecorded(
            this
        );
        consultationRecorded.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void registerConsultationData(
        ConsultationReceived consultationReceived
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Consultation consultation = new Consultation();
        repository().save(consultation);

        ConsultationDataRegistered consultationDataRegistered = new ConsultationDataRegistered(consultation);
        consultationDataRegistered.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(consultationReceived.get???()).ifPresent(consultation->{
            
            consultation // do something
            repository().save(consultation);

            ConsultationDataRegistered consultationDataRegistered = new ConsultationDataRegistered(consultation);
            consultationDataRegistered.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateConsultationData(
        ReservationUpdated reservationUpdated
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Consultation consultation = new Consultation();
        repository().save(consultation);

        ConsultationDataUpdated consultationDataUpdated = new ConsultationDataUpdated(consultation);
        consultationDataUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(reservationUpdated.get???()).ifPresent(consultation->{
            
            consultation // do something
            repository().save(consultation);

            ConsultationDataUpdated consultationDataUpdated = new ConsultationDataUpdated(consultation);
            consultationDataUpdated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateConsultationData(
        ReservationCanceled reservationCanceled
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Consultation consultation = new Consultation();
        repository().save(consultation);

        ConsultationDataUpdated consultationDataUpdated = new ConsultationDataUpdated(consultation);
        consultationDataUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(reservationCanceled.get???()).ifPresent(consultation->{
            
            consultation // do something
            repository().save(consultation);

            ConsultationDataUpdated consultationDataUpdated = new ConsultationDataUpdated(consultation);
            consultationDataUpdated.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
