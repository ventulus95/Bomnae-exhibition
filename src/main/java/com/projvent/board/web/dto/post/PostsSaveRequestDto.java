package com.projvent.board.web.dto.post;

import com.projvent.board.web.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
//    private MultipartFile file;
//    private String filePath;

//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
//        this.filePath = filePath;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
//                .filePath(filePath)
                .build();
    }
}
