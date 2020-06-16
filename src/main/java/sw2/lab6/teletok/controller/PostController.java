package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.lab6.teletok.dto.PostServiceApi;
import sw2.lab6.teletok.entity.Post;
import sw2.lab6.teletok.entity.User;
import sw2.lab6.teletok.repository.PostRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@Controller
public class PostController {


    @Autowired
    PostServiceApi postServiceApi;

    @Autowired
    PostRepository postRepository;

    @GetMapping(value = {"", "/"})
    public String listPost(){


        return "post/list";
    }








    @GetMapping("/post/new")
    public String newPost(Model model, @ModelAttribute("post") Post post){

        return "post/new";
    }

    @PostMapping("/post/save")
    public String savePost(@RequestParam("imageFile") MultipartFile imageFile, @Valid Post post, BindingResult bindingResult
            , RedirectAttributes attr, HttpSession session) {

        User user = new User();
        String returnValue = "redirect:/producto";
        Path pathFinal = null;
        // File  f = null;

        if (bindingResult.hasErrors()) {

            return "producto/editFrm";
        } else {

            if (post.getId() == 0) {


                post.setUser( (User)session.getAttribute("user"));
                post.setCreationDate(new Date());
                post.setMediaUrl(imageFile.getOriginalFilename());


                try {
                    pathFinal = postServiceApi.saveImage(imageFile, post);
                    if (pathFinal == null) {
                        attr.addFlashAttribute("msgImagenPostNull", "No hay imagen");
                        return "post/new";
                    } else {

                        byte[] bytes = imageFile.getBytes();

                        Files.write(pathFinal, bytes);
                    }


                } catch (Exception e) {

                    attr.addFlashAttribute("msgImagenPost", "La imagen seleccionada no existe o no puede estar vacia");
                    return "post/new";
                }


                attr.addFlashAttribute("msg", "Producto creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Producto actualizado exitosamente");
            }


            postRepository.save(post);
            return "redirect:/";
        }



    }

    @GetMapping("/post/file/{media_url}")
    public String getFile() {
        return "";
    }

    @GetMapping("/post/{id}")
    public String viewPost() {
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
