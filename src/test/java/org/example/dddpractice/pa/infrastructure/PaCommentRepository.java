package org.example.dddpractice.pa.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.example.dddpractice.pa.application.port.out.PaCommentPersistencePort;
import org.example.dddpractice.pa.domain.model.PaComment;

public class PaCommentRepository implements PaCommentPersistencePort {

    private final Map<Long, PaComment> db = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public PaComment save(PaComment comment) {
        if (comment.getId() == null) {
            comment.setId(++sequence);
        }

        db.put(comment.getId(), comment);

        return comment;
    }

    @Override
    public List<PaComment> findAllByPostId(Long postId) {
        return db.values().stream()
                .filter(c -> Objects.equals(c.getPostId(), postId))
                .toList();
    }
}
