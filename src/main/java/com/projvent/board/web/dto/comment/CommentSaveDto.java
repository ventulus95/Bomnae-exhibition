package com.projvent.board.web.dto.comment;

import com.projvent.board.web.domain.comment.Comment;
import com.projvent.board.web.domain.post.Post;
import com.projvent.board.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSaveDto {

    private User user;
    private String comment;
    private Post post;


    @Builder
    public CommentSaveDto(String comment, Post post, User user){
        this.comment = comment;
        this.post = post;
        this.user = user;
    }

    public Comment toEntity(){
        return Comment.builder()
                .comment(comment)
                .Post(post)
                .user(user)
                .build();
    }

}
