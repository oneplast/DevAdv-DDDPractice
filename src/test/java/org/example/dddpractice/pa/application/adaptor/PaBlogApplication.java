package org.example.dddpractice.pa.application.adaptor;

import org.example.dddpractice.pa.application.port.in.BlogUseCase;
import org.example.dddpractice.pa.application.port.out.PaPostPersistencePort;
import org.example.dddpractice.pa.domain.model.PaPost;

public class PaBlogApplication implements BlogUseCase {

    private final PaPostPersistencePort postPersistencePort;

    public PaBlogApplication(PaPostPersistencePort postPersistencePort) {
        this.postPersistencePort = postPersistencePort;
    }

    @Override
    public PaPost writePost(String title, String content, String author) {
        PaPost paPost = new PaPost(title, content, author);

        return postPersistencePort.save(paPost);
    }
}
