package org.example.dddpractice.pa.application.port.in;

import java.util.List;
import org.example.dddpractice.pa.domain.model.PaComment;
import org.example.dddpractice.pa.domain.model.PaPost;
import org.example.dddpractice.pa.domain.vo.PaPostDescription;

public interface BlogUseCase {

    PaPost writePost(String title, String content, String author);

    List<PaPost> getPublishedPosts();

    PaPost getPost(Long id);

    void publishPost(Long id);

    PaComment addComment(Long postId, String author, String content);

    PaPostDescription viewPost(Long id);
}
