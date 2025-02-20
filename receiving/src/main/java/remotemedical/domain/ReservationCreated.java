package remotemedical.domain;

import java.util.*;
import lombok.*;
import remotemedical.domain.*;
import remotemedical.infra.AbstractEvent;

@Data
@ToString
public class ReservationCreated extends AbstractEvent {

    private String taskId;
    private String userId;
    private String title;
    private String description;
    private Date dueDate;
}
