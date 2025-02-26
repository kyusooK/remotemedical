package documentmanagement.domain;

import documentmanagement.domain.*;
import documentmanagement.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class FileSaved extends AbstractEvent {

    private Long id;
    private String name;
    private String filePath;
    private String fileType;
    private String previewPath;
    private String status;
    private Date timeStamp;
    private String reason;
}
