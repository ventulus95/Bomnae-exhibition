package com.projvent.board.web.dto.post;

import com.projvent.board.web.domain.post.Post;
import com.projvent.board.web.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class postListResponseDto {

    private Long id;
    private User user;
    private String title;
    private LocalDateTime modifiedDate;

    public postListResponseDto(Post entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
    }
}
