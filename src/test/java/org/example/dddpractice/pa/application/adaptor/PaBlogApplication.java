package org.example.dddpractice.pa.application.adaptor;

import java.util.List;
import org.example.dddpractice.pa.application.port.in.BlogUseCase;
import org.example.dddpractice.pa.application.port.out.PaPostPersistencePort;
import org.example.dddpractice.pa.domain.model.PaPost;
import org.example.dddpractice.pa.domain.model.PaPostStatus;

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

    @Override
    public List<PaPost> getPublishedPosts() {
        return postPersistencePort.findByStatus(PaPostStatus.PUBLISHED);
    }

    @Override
    public PaPost getPost(Long id) {
        return postPersistencePort.findById(id);
    }
}
