package com.projvent.board.service.Guestbook;

import com.projvent.board.config.auth.LoginUser;
import com.projvent.board.config.auth.dto.SessionUser;
import com.projvent.board.web.domain.guestbook.GuestbookRepository;
import com.projvent.board.web.domain.user.User;
import com.projvent.board.web.domain.user.UserRepository;
import com.projvent.board.web.dto.gusetbook.GuestBookSaveDto;
import com.projvent.board.web.dto.gusetbook.GuestbookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestbookService {

    private final UserRepository userRepository;
    private final GuestbookRepository guestbookRepository;

    public Long saveGuestBook(GuestBookSaveDto dto, @LoginUser SessionUser user){
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        dto.setUser(user1.get());
        return guestbookRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<GuestbookResponseDto> findAllDesc(){
        return guestbookRepository.findAllByOrderByModifiedDateDesc().stream()
                .map(GuestbookResponseDto::new)
                .collect(Collectors.toList());
    }
}
