package com.projvent.board.web.controller;

import com.projvent.board.config.auth.LoginUser;
import com.projvent.board.config.auth.dto.SessionUser;
import com.projvent.board.service.comment.CommentService;
import com.projvent.board.web.domain.user.UserRepository;
import com.projvent.board.web.dto.comment.CommentResponseDto;
import com.projvent.board.web.dto.comment.CommentSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserRepository userRepository;

    @GetMapping("/api/v1/posts/{id}/comment")
    public List<CommentResponseDto> getPostByComment(@PathVariable Long id){
        return commentService.findAll(id);
    }

    @PostMapping("/api/v1/posts/{id}/comment")
    public Long save(@RequestBody CommentSaveDto dto, @PathVariable Long id, @LoginUser SessionUser user) throws IOException {
        return commentService.saveComment(id, dto, user);
    }
}
