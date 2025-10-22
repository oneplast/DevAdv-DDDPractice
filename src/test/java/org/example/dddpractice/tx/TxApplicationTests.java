package org.example.dddpractice.tx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.example.dddpractice.tx.domain.TxComment;
import org.example.dddpractice.tx.domain.TxPost;
import org.example.dddpractice.tx.dto.TxPostStatus;
import org.example.dddpractice.tx.repository.TxCommentRepository;
import org.example.dddpractice.tx.repository.TxPostRepository;
import org.example.dddpractice.tx.service.TxBlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TxApplicationTests {

    TxBlogService txBlogService;

    @BeforeEach
    void init() {
        txBlogService = new TxBlogService(new TxPostRepository());
    }

    @Test
    void create_post() {
        String targetTitle = "제목";
        String targetContent = "내용";
        String targetAuthor = "작성자";

        TxPost createdPost = txBlogService.create(targetTitle, targetContent, targetAuthor);

        System.out.println("createdPost = " + createdPost);

        assertNotNull(createdPost.getId());
        assertEquals(TxPostStatus.DRAFT, createdPost.getStatus());
        assertEquals(targetTitle, createdPost.getTitle());
    }

    @Test
    void comment_test() {
        String targetContent = "댓글 제목";
        String targetAuthor = "작성자1";
        long targetPostId = 1L;

        TxCommentRepository commentRepository = new TxCommentRepository();

        TxComment comment = TxComment.builder()
                .postId(targetPostId)
                .author(targetAuthor)
                .content(targetContent)
                .build();

        TxComment createdComment = commentRepository.save(comment);

        assertNotNull(createdComment.getId());
    }
}
