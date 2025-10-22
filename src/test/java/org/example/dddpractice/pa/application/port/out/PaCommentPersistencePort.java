package org.example.dddpractice.pa.application.port.out;

import java.util.List;
import org.example.dddpractice.pa.domain.model.PaComment;

public interface PaCommentPersistencePort {
    PaComment save(PaComment comment);

    List<PaComment> findAllByPostId(Long postId);
}
