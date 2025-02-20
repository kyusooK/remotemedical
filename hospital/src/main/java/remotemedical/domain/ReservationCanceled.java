package remotemedical.domain;

import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

@Data
@ToString
public class ReservationCanceled extends AbstractEvent {

    private Long id;
    private Object reservationType;
}
