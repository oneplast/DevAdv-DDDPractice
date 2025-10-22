package org.example.dddpractice.pa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.example.dddpractice.pa.application.adaptor.PaBlogApplication;
import org.example.dddpractice.pa.domain.model.PaComment;
import org.example.dddpractice.pa.domain.model.PaPost;
import org.example.dddpractice.pa.domain.model.PaPostStatus;
import org.example.dddpractice.pa.domain.service.PaPostDomainService;
import org.example.dddpractice.pa.domain.vo.PaCommentDescription;
import org.example.dddpractice.pa.domain.vo.PaPostDescription;
import org.example.dddpractice.pa.infrastructure.PaCommentRepository;
import org.example.dddpractice.pa.infrastructure.PaPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PaBlogApplicationTests {

    PaBlogApplication application;

    @BeforeEach
    void init() {
        PaPostDomainService domainService = new PaPostDomainService();
        PaPostRepository postRepository = new PaPostRepository();
        PaCommentRepository commentRepository = new PaCommentRepository();
        application = new PaBlogApplication(postRepository, domainService, commentRepository);
    }

    @Test
    void write_post() {
        String givenTitle = "제목1";
        String givenContent = "내용1";
        String givenAuthor = "작성자1";

        PaPost whenSaved = application.writePost(givenTitle, givenContent, givenAuthor);

        assertNotNull(whenSaved.getId());
        assertEquals(PaPostStatus.DRAFT, whenSaved.getStatus());
        assertEquals(givenTitle, whenSaved.getTitle());
    }

    @Nested
    public class Context_with_post {
        PaPost post;
        String givenTitle = "제목1";
        String givenContent = "내용1";
        String givenAuthor = "작성자1";

        @BeforeEach
        void initPost() {
            post = application.writePost(givenTitle, givenContent, givenAuthor);
        }

        @Test
        void publish_post() throws Exception {
            application.publishPost(post.getId());
            PaPost findPost = application.getPost(post.getId());

            assertNotNull(findPost);
            assertEquals(findPost.getStatus(), PaPostStatus.PUBLISHED);
        }

        @Test
        void get_published_posts() throws Exception {
            application.publishPost(post.getId());
            List<PaPost> publishedPosts = application.getPublishedPosts();
            PaPost first = publishedPosts.getFirst();

            assertEquals(1, publishedPosts.size());
            assertEquals(PaPostStatus.PUBLISHED, first.getStatus());
        }

        @Test
        void add_comment() throws Exception {
            application.publishPost(post.getId());

            String given_comment_author = "댓글_작성자_1";
            String given_comment_content = "댓글_내용_1";

            PaComment comment = application.addComment(post.getId(), given_comment_author, given_comment_content);

            assertNotNull(comment);
            assertEquals(comment.getContent(), given_comment_content);
        }

        @Test
        void view_post() throws Exception {
            application.publishPost(post.getId());

            String comment_author = "댓글_작성자_2";
            String comment_content = "댓글_내용_2";

            application.addComment(post.getId(), comment_author, comment_content);

            PaPostDescription postWithComments = application.viewPost(post.getId());
            List<PaCommentDescription> comments = postWithComments.comments();
            PaCommentDescription firstComment = comments.getFirst();

            assertNotNull(postWithComments);
            assertEquals(postWithComments.viewCount(), 1);
            assertEquals(comments.size(), 1);
            assertEquals(firstComment.author(), comment_author);
        }
    }
}