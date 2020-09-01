package com.projvent.board.web.dto;

import com.projvent.board.web.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class postListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public postListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
