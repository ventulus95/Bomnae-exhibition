package com.projvent.board.web;

import com.projvent.board.service.comment.CommentService;
import com.projvent.board.web.dto.comment.CommentResponseDto;
import com.projvent.board.web.dto.comment.CommentSaveDto;
import com.projvent.board.web.dto.post.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/v1/posts/{id}/comment")
    public List<CommentResponseDto> getPostByComment(@PathVariable Long id){
        return commentService.findAll(id);
    }

    @PostMapping("/api/v1/posts/{id}/comment")
    public Long save(@RequestBody CommentSaveDto dto, @PathVariable Long id) throws IOException {
        return commentService.saveComment(id, dto);
    }
}
