package sw2.lab6.teletok.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import sw2.lab6.teletok.entity.Post;

import java.nio.file.Path;

public interface PostServiceApi {


    Path saveImage(MultipartFile imageFile, Post post) throws Exception;



}
