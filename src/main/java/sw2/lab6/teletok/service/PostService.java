package sw2.lab6.teletok.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sw2.lab6.teletok.dto.PostServiceApi;
import sw2.lab6.teletok.entity.Post;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PostService implements PostServiceApi {



    @Override
    public Path saveImage(MultipartFile imageFile, Post post) throws Exception {
        String pathPost = null;
        Path path = null;

        pathPost = ("D://Teletok//");

        if (imageFile.getOriginalFilename() != null) {

            path = Paths.get(pathPost + imageFile.getOriginalFilename());
        }else{

            path = null;
        }
        return path ;
    }





}
