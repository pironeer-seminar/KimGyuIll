package com.pironeer.week6.post.component;

import com.pironeer.week6.post.repository.PostRepository;
import com.pironeer.week6.user.dto.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostGuard {

    private final PostRepository postRepository;

    public boolean check(Long postId, MemberPrincipal principal) {
        return postRepository.findById(postId)
                .map(post -> post.getAuthor().getId().equals(principal.getUser().getId()))
                .orElse(false);
    }
}
