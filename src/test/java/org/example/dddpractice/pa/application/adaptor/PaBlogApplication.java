package org.example.dddpractice.pa.application.adaptor;

import java.util.List;
import org.example.dddpractice.pa.application.port.in.BlogUseCase;
import org.example.dddpractice.pa.application.port.out.PaCommentPersistencePort;
import org.example.dddpractice.pa.application.port.out.PaPostPersistencePort;
import org.example.dddpractice.pa.domain.model.PaComment;
import org.example.dddpractice.pa.domain.model.PaPost;
import org.example.dddpractice.pa.domain.model.PaPostStatus;
import org.example.dddpractice.pa.domain.service.PaPostDomainService;
import org.example.dddpractice.pa.domain.vo.PaPostDescription;

public class PaBlogApplication implements BlogUseCase {

    private final PaPostDomainService postDomainService;
    private final PaPostPersistencePort postPersistencePort;
    private final PaCommentPersistencePort commentPersistencePort;

    public PaBlogApplication(PaPostPersistencePort postPersistencePort, PaPostDomainService postDomainService,
                             PaCommentPersistencePort commentPersistencePort) {
        this.postDomainService = postDomainService;
        this.postPersistencePort = postPersistencePort;
        this.commentPersistencePort = commentPersistencePort;
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

    @Override
    public void publishPost(Long id) {
        PaPost post = findPostById(id);
        post.publish();

        postPersistencePort.save(post);
    }

    @Override
    public PaComment addComment(Long postId, String author, String content) {
        PaComment paComment = new PaComment(content, author, postId);

        return commentPersistencePort.save(paComment);
    }

    private PaPost findPostById(Long postId) {
        PaPost findPost = postPersistencePort.findById(postId);

        if (findPost == null) {
            throw new IllegalArgumentException("%d번 포스트를 찾을 수 없습니다.".formatted(postId));
        }

        return findPost;
    }

    @Override
    public PaPostDescription viewPost(Long id) {
        PaPost post = findPostById(id);

        post.increaseViewCount();
        postPersistencePort.save(post);

        List<PaComment> comments = commentPersistencePort.findAllByPostId(id);

        return postDomainService.createPostDescription(post, comments);
    }
}
