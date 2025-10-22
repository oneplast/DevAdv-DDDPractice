package org.example.dddpractice.pa.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.dddpractice.pa.application.port.out.PaPostPersistencePort;
import org.example.dddpractice.pa.domain.model.PaPost;
import org.example.dddpractice.pa.domain.model.PaPostStatus;

public class PaPostRepository implements PaPostPersistencePort {

    private final Map<Long, PaPost> db = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public PaPost save(PaPost post) {
        if (post.getId() == null) {
            post.setId(++sequence);
        }

        db.put(post.getId(), post);

        return post;
    }

    @Override
    public List<PaPost> findByStatus(PaPostStatus status) {
        return db.values().stream()
                .filter(post -> post.getStatus() == status)
                .toList();
    }

    @Override
    public PaPost findById(Long id) {
        return db.get(id);
    }
}
