package remotemedical.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class InsuranceCharged extends AbstractEvent {

    private Long id;
    private Date consultationDate;
    private String symptoms;
    private String paymentId;
    private String consultationExpense;

    public InsuranceCharged(Insurance aggregate) {
        super(aggregate);
    }

    public InsuranceCharged() {
        super();
    }
}
//>>> DDD / Domain Event
