package com.projvent.board.service.Guestbook;

import com.projvent.board.web.domain.guestbook.Guestbook;
import com.projvent.board.web.domain.guestbook.GuestbookRepository;
import com.projvent.board.web.dto.gusetbook.GuestBookSaveDto;
import com.projvent.board.web.dto.gusetbook.GuestbookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestbookService {

    private  final GuestbookRepository guestbookRepository;

    public Long saveGuestBook(GuestBookSaveDto dto){
        return guestbookRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<GuestbookResponseDto> findAllDesc(){
        return guestbookRepository.findAllByOrderByModifiedDateDesc().stream()
                .map(GuestbookResponseDto::new)
                .collect(Collectors.toList());
    }
}
