package org.example.dddpractice.pa.application.port.in;

import java.util.List;
import org.example.dddpractice.pa.domain.model.PaPost;

public interface BlogUseCase {

    PaPost writePost(String title, String content, String author);

    List<PaPost> getPublishedPosts();

    PaPost getPost(Long id);
}
