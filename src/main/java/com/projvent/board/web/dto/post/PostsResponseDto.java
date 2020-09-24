package com.projvent.board.web.dto.post;

import com.projvent.board.web.domain.post.Post;
import com.projvent.board.web.domain.user.User;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private User user;
    private String title;
    private String content;

    public PostsResponseDto(Post entity){
        this.id = entity.getId();
        this.user = entity.getUser();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
