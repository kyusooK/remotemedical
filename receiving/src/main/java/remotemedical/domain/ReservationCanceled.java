package remotemedical.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservationCanceled extends AbstractEvent {

    private ReservationType reservationType;

    public ReservationCanceled(Receiving aggregate) {
        super(aggregate);
    }

    public ReservationCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
