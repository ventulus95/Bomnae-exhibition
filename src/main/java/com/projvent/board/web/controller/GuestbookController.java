package com.projvent.board.web.controller;

import com.projvent.board.config.auth.LoginUser;
import com.projvent.board.config.auth.dto.SessionUser;
import com.projvent.board.service.Guestbook.GuestbookService;
import com.projvent.board.web.dto.gusetbook.GuestBookSaveDto;
import com.projvent.board.web.dto.gusetbook.GuestbookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("/gusetbook-list")
    public List<GuestbookResponseDto> getPostByComment(){
        return guestbookService.findAllDesc();
    }

    @PostMapping("/guestbook")
    public Long save(@RequestBody GuestBookSaveDto dto, @LoginUser SessionUser user) throws IOException {
        return guestbookService.saveGuestBook(dto, user);
    }

}
