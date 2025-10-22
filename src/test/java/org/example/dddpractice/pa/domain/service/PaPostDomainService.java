package org.example.dddpractice.pa.domain.service;

import java.util.List;
import org.example.dddpractice.pa.domain.model.PaComment;
import org.example.dddpractice.pa.domain.model.PaPost;
import org.example.dddpractice.pa.domain.vo.PaCommentDescription;
import org.example.dddpractice.pa.domain.vo.PaPostDescription;

public class PaPostDomainService {

    public PaPostDescription createPostDescription(PaPost post, List<PaComment> comments) {
        return new PaPostDescription(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getViewCount(),
                comments.stream()
                        .map(c -> new PaCommentDescription(c.getAuthor(), c.getContent(), c.getCreatedAt()))
                        .toList());
    }
}
