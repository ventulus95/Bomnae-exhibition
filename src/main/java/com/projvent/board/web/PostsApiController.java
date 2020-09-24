package com.projvent.board.web;

import com.projvent.board.service.posts.PostsService;
import com.projvent.board.web.domain.upload.S3Service;
import com.projvent.board.web.dto.post.PostsResponseDto;
import com.projvent.board.web.dto.post.PostsSaveRequestDto;
import com.projvent.board.web.dto.post.PostsUpadteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final S3Service s3Service;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto dto) throws IOException {
//        String imgpath  = s3Service.upload(dto.getFile());
//        dto.setFilePath(imgpath);
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
