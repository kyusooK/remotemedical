package remotemedical.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ConsultationDataRegistered extends AbstractEvent {

    private Long id;
    private String patientName;
    private String patientSymptoms;
    private String pastHistory;
    private ConsultationStatus consultationStatus;

    public ConsultationDataRegistered(Consultation aggregate) {
        super(aggregate);
    }

    public ConsultationDataRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
