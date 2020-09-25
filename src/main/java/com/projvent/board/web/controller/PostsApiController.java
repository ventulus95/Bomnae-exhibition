package com.projvent.board.web.controller;

import com.projvent.board.config.auth.LoginUser;
import com.projvent.board.config.auth.dto.SessionUser;
import com.projvent.board.service.posts.PostsService;
import com.projvent.board.service.upload.S3Service;
import com.projvent.board.web.domain.user.User;
import com.projvent.board.web.domain.user.UserRepository;
import com.projvent.board.web.dto.post.PostsResponseDto;
import com.projvent.board.web.dto.post.PostsSaveRequestDto;
import com.projvent.board.web.dto.post.PostsUpadteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto dto, @LoginUser SessionUser user) throws IOException {
//        String imgpath  = s3Service.upload(dto.getFile());
//        dto.setFilePath(imgpath);
        return postsService.save(dto, user);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpadteRequestDto dto ){
        return postsService.update(id, dto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id, @LoginUser SessionUser user){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long postDelete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
