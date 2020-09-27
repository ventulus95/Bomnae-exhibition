package com.projvent.board.web.dto.post;

import com.projvent.board.web.domain.post.Post;
import com.projvent.board.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@RequiredArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private User user;
    private MultipartFile file;
    private String filePath;


    @Builder
    public PostsSaveRequestDto(String title, String content, User user, MultipartFile file, String filePath){
        this.title = title;
        this.content = content;
        this.user = user;
        this.file = file;
        this.filePath = filePath;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .filePath(filePath)
                .build();
    }
}
