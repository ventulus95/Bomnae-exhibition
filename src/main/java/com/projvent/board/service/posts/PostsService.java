package com.projvent.board.service.posts;

import com.projvent.board.web.domain.post.Post;
import com.projvent.board.web.domain.post.PostRepository;
import com.projvent.board.web.dto.PostsResponseDto;
import com.projvent.board.web.dto.PostsSaveRequestDto;
import com.projvent.board.web.dto.PostsUpadteRequestDto;
import com.projvent.board.web.dto.postListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostRepository postRepository;

    public Long save(PostsSaveRequestDto dto) {
        return postRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpadteRequestDto dto) {
        Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        post.update(dto.getTitle(), dto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
        return new PostsResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<postListResponseDto> findAllDesc(){
        return postRepository.findALlDesc().stream()
                .map(postListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        postRepository.deleteById(id);

    }
}
