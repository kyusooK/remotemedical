package remotemedical.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import remotemedical.domain.*;

@Component
public class ConsultationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Consultation>> {

    @Override
    public EntityModel<Consultation> process(EntityModel<Consultation> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/progressvideoconsultation"
                )
                .withRel("progressvideoconsultation")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/recordconsultation"
                )
                .withRel("recordconsultation")
        );

        return model;
    }
}
