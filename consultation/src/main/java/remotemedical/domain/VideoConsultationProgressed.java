package remotemedical.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class VideoConsultationProgressed extends AbstractEvent {

    private Long id;
    private String patientName;
    private String patientSymptoms;
    private String videoId;
    private String pastHistory;
    private ConsultationStatus consultationStatus;

    public VideoConsultationProgressed(Consultation aggregate) {
        super(aggregate);
    }

    public VideoConsultationProgressed() {
        super();
    }
}
//>>> DDD / Domain Event
