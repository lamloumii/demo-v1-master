package com.example.demo.Controller;


import com.example.demo.entity.File;
import com.example.demo.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/File")
@AllArgsConstructor
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping("/uploadFile")
    public ResponseEntity<File> uploadFile(@RequestParam MultipartFile file) {
        try {
            File fileEntity = fileService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/files/download/")
                    .path(fileEntity.getId().toString())
                    .toUriString();
            fileEntity.setFilePath(fileDownloadUri); // Update with URL to download
            return ResponseEntity.ok(fileEntity);
        } catch (IOException ex) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId, HttpServletRequest request) {
//        File fileEntity = fileService.getFile(fileId);
//        if (fileEntity == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Path filePath = fileService.loadFileAsResource(fileEntity.getFileName());
//        try {
//            Resource resource = (Resource) new UrlResource(filePath.toUri());
//            if(resource.exists()) {
//                String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//                return ResponseEntity.ok()
//                        .contentType(MediaType.parseMediaType(contentType))
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                        .body(resource);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (MalformedURLException ex) {
//            return ResponseEntity.badRequest().build();
//        }
//    }


    @GetMapping("/getAll")
    public List<File> getAllFile() {
        return fileService.getAllFile();
    }

    @GetMapping("/get/{id}")
    public Optional<File> getFileById(@PathVariable Long id) {
        return fileService.getFileById(id);
    }

    @PutMapping("/updateFile{id}")
    public ResponseEntity<File> updateLocation(@PathVariable Long id, @RequestBody File FileDetails) {
        File updatedFile = fileService.updateFile(id, FileDetails);
        return ResponseEntity.ok(updatedFile);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) {
        try {
            // Check if the file exists
            Optional<File> fileOptional = fileService.getFileById(id);
            if (!fileOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            // Delete the file from the file system
            fileService.deleteFile(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }
}









