package remotemedical.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import remotemedical.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "receivings",
    path = "receivings"
)
public interface ReceivingRepository
    extends PagingAndSortingRepository<Receiving, Long> {}
