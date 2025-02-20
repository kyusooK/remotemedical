package remotemedical.domain;

import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

@Data
@ToString
public class ConsultationReceived extends AbstractEvent {

    private Long id;
    private String userName;
    private String symptoms;
    private Date reservationDate;
    private String pastHistory;
    private Object reservationType;
}
