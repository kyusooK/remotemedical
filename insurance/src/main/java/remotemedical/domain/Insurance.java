package remotemedical.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import remotemedical.InsuranceApplication;

@Entity
@Table(name = "Insurance_table")
@Data
//<<< DDD / Aggregate Root
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date consultationDate;

    private Long paymentId;

    private Integer consultationExpense;

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
        
        Insurance insurance = new Insurance();

        insurance.setConsultationDate(new Date());
        insurance.setPaymentId(requstPaymentCompleted.getId());
        insurance.setConsultationExpense(requstPaymentCompleted.getPrice());

        repository().save(insurance);

        InsuranceCharged insuranceCharged = new InsuranceCharged(insurance);
        insuranceCharged.publishAfterCommit();

        

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
