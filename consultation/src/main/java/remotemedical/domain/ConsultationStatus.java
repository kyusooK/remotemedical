package remotemedical.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public enum ConsultationStatus {
    RESERVED,
    PROGRESSED,
    COMPLETED,
    CANCELED,
}
