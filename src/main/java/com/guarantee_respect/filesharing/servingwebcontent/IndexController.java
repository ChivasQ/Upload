package com.guarantee_respect.filesharing.servingwebcontent;

import com.guarantee_respect.filesharing.models.FileEntity;
import com.guarantee_respect.filesharing.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class IndexController {
    private final String saveDir = "storage/";
    private final Path rootLocation = Paths.get("");

    @Autowired
    private FileRepository fileRepository;

    @GetMapping("/upload")
    public String upload(Model model) {
        return "upload";
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "test";
    }


    @PostMapping("/")
    public String uploadPost(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        try {
            if (!file.isEmpty()) {
                Path path = Paths.get(saveDir, file.getOriginalFilename()).normalize();

                Path destFile = this.rootLocation.resolve(path.toAbsolutePath());

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, destFile, StandardCopyOption.REPLACE_EXISTING);
                    FileEntity fileEntity = new FileEntity(file.getOriginalFilename(), path.toString(), file.getContentType());
                    System.out.println(fileEntity.getFilePath());
                    fileRepository.save(fileEntity);
                    return "redirect:/file/" + fileEntity.getId();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/upload";
    }


}
