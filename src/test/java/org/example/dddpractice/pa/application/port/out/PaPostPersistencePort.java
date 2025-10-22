package org.example.dddpractice.pa.application.port.out;

import java.util.List;
import org.example.dddpractice.pa.domain.model.PaPost;
import org.example.dddpractice.pa.domain.model.PaPostStatus;

public interface PaPostPersistencePort {

    PaPost save(PaPost paPost);

    List<PaPost> findByStatus(PaPostStatus status);

    PaPost findById(Long id);
}
