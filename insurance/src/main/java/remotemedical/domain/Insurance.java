package remotemedical.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import remotemedical.InsuranceApplication;
import remotemedical.domain.InsuranceCharged;

@Entity
@Table(name = "Insurance_table")
@Data
//<<< DDD / Aggregate Root
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date consultationDate;

    private String symptoms;

    private String paymentId;

    private String consultationExpense;

    public static InsuranceRepository repository() {
        InsuranceRepository insuranceRepository = InsuranceApplication.applicationContext.getBean(
            InsuranceRepository.class
        );
        return insuranceRepository;
    }

    //<<< Clean Arch / Port Method
    public static void chargeInsurance(
        RequstPaymentCompleted requstPaymentCompleted
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Insurance insurance = new Insurance();
        repository().save(insurance);

        InsuranceCharged insuranceCharged = new InsuranceCharged(insurance);
        insuranceCharged.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(requstPaymentCompleted.get???()).ifPresent(insurance->{
            
            insurance // do something
            repository().save(insurance);

            InsuranceCharged insuranceCharged = new InsuranceCharged(insurance);
            insuranceCharged.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
