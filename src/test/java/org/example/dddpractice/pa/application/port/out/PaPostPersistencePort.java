package org.example.dddpractice.pa.application.port.out;

import org.example.dddpractice.pa.domain.model.PaPost;

public interface PaPostPersistencePort {

    PaPost save(PaPost paPost);
}
