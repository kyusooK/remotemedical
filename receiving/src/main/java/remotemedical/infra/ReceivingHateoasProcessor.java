package remotemedical.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import remotemedical.domain.*;

@Component
public class ReceivingHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Receiving>> {

    @Override
    public EntityModel<Receiving> process(EntityModel<Receiving> model) {
        return model;
    }
}
