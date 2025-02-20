package remotemedical.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ConsultationReceived extends AbstractEvent {

    private Long id;
    private String userName;
    private String symptoms;
    private Date reservationDate;
    private String pastHistory;
    private ReservationType reservationType;

    public ConsultationReceived(Receiving aggregate) {
        super(aggregate);
    }

    public ConsultationReceived() {
        super();
    }
}
//>>> DDD / Domain Event
