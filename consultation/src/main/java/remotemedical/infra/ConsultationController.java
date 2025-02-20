package remotemedical.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import remotemedical.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/consultations")
@Transactional
public class ConsultationController {

    @Autowired
    ConsultationRepository consultationRepository;

    @RequestMapping(
        value = "/consultations/{id}/progressvideoconsultation",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Consultation progressVideoconsultation(
        @PathVariable(value = "id") Long id,
        @RequestBody ProgressVideoconsultationCommand progressVideoconsultationCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /consultation/progressVideoconsultation  called #####"
        );
        Optional<Consultation> optionalConsultation = consultationRepository.findById(
            id
        );

        optionalConsultation.orElseThrow(() -> new Exception("No Entity Found")
        );
        Consultation consultation = optionalConsultation.get();
        consultation.progressVideoconsultation(
            progressVideoconsultationCommand
        );

        consultationRepository.save(consultation);
        return consultation;
    }

    @RequestMapping(
        value = "/consultations/recordconsultation",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Consultation recordConsultation(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RecordConsultationCommand recordConsultationCommand
    ) throws Exception {
        System.out.println(
            "##### /consultation/recordConsultation  called #####"
        );
        Consultation consultation = new Consultation();
        consultation.recordConsultation(recordConsultationCommand);
        consultationRepository.save(consultation);
        return consultation;
    }
}
//>>> Clean Arch / Inbound Adaptor
