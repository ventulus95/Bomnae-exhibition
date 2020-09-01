package com.projvent.board.web;

import com.projvent.board.service.posts.PostsService;
import com.projvent.board.web.dto.PostsResponseDto;
import com.projvent.board.web.dto.PostsSaveRequestDto;
import com.projvent.board.web.dto.PostsUpadteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto dto){
        return postsService.save(dto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpadteRequestDto dto ){
        return postsService.update(id, dto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
