package com.projvent.board.web.dto.comment;

import com.projvent.board.web.domain.comment.Comment;
import com.projvent.board.web.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSaveDto {


    private String comment;
    private Post post;


    @Builder
    public CommentSaveDto(String comment, Post post){
        this.comment = comment;
        this.post = post;
    }

    public Comment toEntity(){
        return Comment.builder()
                .comment(comment)
                .Post(post)
                .build();
    }

}
