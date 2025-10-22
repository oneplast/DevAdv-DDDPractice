package org.example.dddpractice.tx.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.dddpractice.tx.dto.TxPostStatus;

@Getter
@Setter
@ToString
public class TxPost {

    private Long id;

    private String title;
    private String content;

    private String author;

    private TxPostStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int viewCount;

    @Builder
    public TxPost(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;

        this.status = TxPostStatus.DRAFT;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.viewCount = 0;
    }
}

