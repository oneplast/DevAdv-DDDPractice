package org.example.dddpractice.pa.domain.vo;

import java.time.LocalDateTime;

public record PaCommentDescription(
        String author,
        String content,
        LocalDateTime createdAt
) {
}
