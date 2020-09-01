package com.projvent.board;

import com.projvent.board.web.domain.post.Post;
import com.projvent.board.web.domain.post.PostRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After
    public void cleanUp(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글_생성(){
        String title = "안녕하세요.";
        String content = "이게 바로 콘텐츠다!";

        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .author("lee")
                .build());

        List<Post> list = postRepository.findAll();

        Post post = list.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseEntitiy(){
        LocalDateTime now = LocalDateTime.of(2020, 8,11,0,0,0);
        postRepository.save(Post.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Post> list = postRepository.findAll();

        Post post = list.get(0);

        System.out.println(">>>>>>>>>>>>> created date="+post.getCreatedDate()+", modified Date = "+post.getModifiedDate());

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }

}
