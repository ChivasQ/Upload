package com.guarantee_respect.filesharing.servingwebcontent;

import com.guarantee_respect.filesharing.models.FileEntity;
import com.guarantee_respect.filesharing.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
public class FileController {
    @Autowired
    private FileRepository fileRepository;


    @GetMapping("file/{id}")
    public String filePage(@PathVariable(value = "id") UUID id, Model model) {
        Optional<FileEntity> fileEntity = fileRepository.findById(id);
        if (fileEntity.isEmpty()) {
            return "error";
        }
        model.addAttribute("filer", fileEntity.get());
        System.out.println(fileEntity.get().getFilePath());

        return switch (fileEntity.get().getContentType()) {
            case ("video/mp4"), ("video/quicktime") -> "video";
            case ("audio/mpeg") -> "audio";
            default -> "file";
        };
    }

}
