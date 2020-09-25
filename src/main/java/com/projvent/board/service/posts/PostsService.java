package com.projvent.board.service.posts;

import com.projvent.board.config.auth.LoginUser;
import com.projvent.board.config.auth.dto.SessionUser;
import com.projvent.board.web.domain.post.Post;
import com.projvent.board.web.domain.post.PostRepository;
import com.projvent.board.web.domain.user.User;
import com.projvent.board.web.domain.user.UserRepository;
import com.projvent.board.web.dto.post.PostsResponseDto;
import com.projvent.board.web.dto.post.PostsSaveRequestDto;
import com.projvent.board.web.dto.post.PostsUpadteRequestDto;
import com.projvent.board.web.dto.post.postListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long save(PostsSaveRequestDto dto, @LoginUser SessionUser user) {
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        dto.setUser(user1.get());
        return postRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpadteRequestDto dto) {
        Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        post.update(dto.getTitle(), dto.getContent(), dto.getTitle());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
        return new PostsResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<postListResponseDto> findAllDesc(){
        return postRepository.findAllDesc().stream()
                .map(postListResponseDto::new)
                .collect(Collectors.toList());
     }

    @Transactional
    public void delete(Long id){
        postRepository.deleteById(id);

    }
}
