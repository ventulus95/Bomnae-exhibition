package com.projvent.board.web.dto.comment;

import com.projvent.board.web.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;
    private String comment;
    private LocalDateTime modifiedDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.modifiedDate = comment.getModifiedDate();
    }
}
