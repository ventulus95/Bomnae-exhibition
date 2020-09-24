package com.projvent.board.service.comment;

import com.projvent.board.web.domain.comment.CommentRepository;
import com.projvent.board.web.domain.post.Post;
import com.projvent.board.web.domain.post.PostRepository;
import com.projvent.board.web.dto.comment.CommentResponseDto;
import com.projvent.board.web.dto.comment.CommentSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Long saveComment(Long id, CommentSaveDto dto){
        Optional<Post> post = postRepository.findById(id);
        dto.setPost(post.get());
        return commentRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll(Long id){
        Optional<Post> post = postRepository.findById(id);
        return commentRepository.findAllByPost(post.get())
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }


}
