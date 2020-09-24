package com.projvent.board.web;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.projvent.board.config.auth.LoginUser;
import com.projvent.board.config.auth.dto.SessionUser;
import com.projvent.board.service.comment.CommentService;
import com.projvent.board.service.posts.PostsService;
import com.projvent.board.web.domain.upload.S3Service;
import com.projvent.board.web.dto.comment.CommentResponseDto;
import com.projvent.board.web.dto.post.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final CommentService commentService;
    private final HttpSession httpSession;
    private final S3Service s3Service;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        if(user!=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("img", user.getPicture());
        }
        return "index"; 
    }

    @GetMapping("/post-save")
    public String post(){
        return "post-save";
    }

    @GetMapping("/post/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        List<CommentResponseDto> commentlist =commentService.findAll(id);
        model.addAttribute("post", dto);
        model.addAttribute("list", commentlist);
        return "post-update";
    }

    @DeleteMapping("/api/v1/posts/{id}")
    @ResponseBody
    public Long postDelete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

    @GetMapping("/upload")
    public String u(){
        return "upload";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/sign-in")
    public String signin(){
        return "sign-in";
    }

    @PostMapping("/api/v1/upload")
    public String Upload(@RequestPart MultipartFile file) throws IOException, ImageProcessingException {
        System.out.println(file.getOriginalFilename());
        File dest = new File("/Users/LeeChnagSup/Desktop/board/src/main/resources/static/Image/"+file.getOriginalFilename());
        file.transferTo(dest);
        Metadata metadata = ImageMetadataReader.readMetadata(dest);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
        return "redirect:/";

    }

    @PostMapping("/api/v1/s3upload")
    public String execWrite( MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(file);
        System.out.println(imgPath);
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
