package remotemedical.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ConsultationRecorded extends AbstractEvent {

    private Long id;
    private String consultationContent;
    private ConsultationStatus consultationStatus;

    public ConsultationRecorded(Consultation aggregate) {
        super(aggregate);
    }

    public ConsultationRecorded() {
        super();
    }
}
//>>> DDD / Domain Event
