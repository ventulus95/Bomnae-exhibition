package com.projvent.board.web.dto.gusetbook;

import com.projvent.board.web.domain.guestbook.Guestbook;
import com.projvent.board.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestBookSaveDto {

    private User user;
    private String comment;

    @Builder
    public GuestBookSaveDto(User user, String comment){
        this.user = user;
        this.comment = comment;
    }

    public Guestbook toEntity(){
        return Guestbook.builder()
                .user(user)
                .gusetBook(comment)
                .build();
    }

}
