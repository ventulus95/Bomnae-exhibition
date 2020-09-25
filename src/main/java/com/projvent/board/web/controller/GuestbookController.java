package com.projvent.board.web.controller;

import com.projvent.board.config.auth.LoginUser;
import com.projvent.board.config.auth.dto.SessionUser;
import com.projvent.board.service.Guestbook.GuestbookService;
import com.projvent.board.web.domain.user.User;
import com.projvent.board.web.domain.user.UserRepository;
import com.projvent.board.web.dto.gusetbook.GuestBookSaveDto;
import com.projvent.board.web.dto.gusetbook.GuestbookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;
    private final UserRepository userRepository;

    @GetMapping("/gusetbook-list")
    public List<GuestbookResponseDto> getPostByComment(){
        return guestbookService.findAllDesc();
    }

    @PostMapping("/guestbook")
    public Long save(@RequestBody GuestBookSaveDto dto, @LoginUser SessionUser user) throws IOException {
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        dto.setUser(user1.get());
        return guestbookService.saveGuestBook(dto);
    }

}
