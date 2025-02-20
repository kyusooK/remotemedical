package remotemedical.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ConsultationDataUpdated extends AbstractEvent {

    private Long id;
    private String patientName;
    private String patientSymptoms;
    private String pastHistory;
    private String consultationContent;
    private ConsultationStatus consultationStatus;

    public ConsultationDataUpdated(Consultation aggregate) {
        super(aggregate);
    }

    public ConsultationDataUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
