package com.projvent.board.web.dto.gusetbook;

import com.projvent.board.web.domain.guestbook.Guestbook;
import com.projvent.board.web.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GuestbookResponseDto {

    private String guestbook;
    private User user;
    private LocalDateTime modifiedDate;

    public GuestbookResponseDto(Guestbook guestBook) {
        this.guestbook = guestBook.getGusetBook();
        this.user = guestBook.getUser();
        this.modifiedDate = guestBook.getModifiedDate();
    }
}
