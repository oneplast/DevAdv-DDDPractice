package org.example.dddpractice.pa.application.port.in;

import org.example.dddpractice.pa.domain.model.PaPost;

public interface BlogUseCase {

    PaPost writePost(String title, String content, String author);
}
