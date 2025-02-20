package remotemedical.domain;

import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

@Data
@ToString
public class ReservationUpdated extends AbstractEvent {

    private Long id;
    private Date reservationDate;
    private Object reservationType;
    private String symptoms;
    private String pastHistory;
}
