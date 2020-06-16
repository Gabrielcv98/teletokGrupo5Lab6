package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sw2.lab6.teletok.entity.Post;
import sw2.lab6.teletok.repository.PostCommentRepository;
import sw2.lab6.teletok.repository.PostRepository;

import java.util.List;


@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository postCommentRepositoryRepository;


    @GetMapping(value = {"", "/"})
    public String listPost(){
        return "post/list";
    }

    @GetMapping("/post/new")
    public String newPost(){
        return "post/new";
    }

    @PostMapping("/post/save")
    public String savePost() {
        return "redirect:/";
    }

    @GetMapping("/post/file/{media_url}")
    public String getFile() {



        return "";
    }

    @GetMapping("/post/{id}")
    public String viewPost(Model model,@RequestParam("id") int id) {

        model.addAttribute("listaPost",postRepository.findById(int id));
        model.addAttribute("listaPostComment",postRepository.findById(int id));

        return "post/view";
    }

    @PostMapping("/post/comment")
    public String postComment() {



        return "";
    }

    @PostMapping("/post/like")
    public String postLike() {


        return "";
    }
}
