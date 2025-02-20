package remotemedical.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RecordConsultationCommand {

    private Long id;
    private String consultationContent;
}
