package remotemedical.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservationUpdated extends AbstractEvent {

    private Long id;
    private Date reservationDate;
    private ReservationType reservationType;
    private String symptoms;
    private String pastHistory;

    public ReservationUpdated(Receiving aggregate) {
        super(aggregate);
    }

    public ReservationUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
