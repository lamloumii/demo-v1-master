package com.example.demo.service;


import com.example.demo.entity.File;
import com.example.demo.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepo ;
    private Path fileStorageLocation; // Path where the files will be stored

    @Autowired
    public void FileStorageService(FileRepository fileRepository) {
        this.fileRepo = fileRepository;
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }



    public File storeFile(MultipartFile file) throws IOException {
        // Clean the file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        // Copy file to the target location (Replacing existing file with the same name)
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
        Path targetLocation = this.fileStorageLocation.resolve(newFileName);
        Files.copy(file.getInputStream(), targetLocation);

        // Save the file details in the database
        File fileEntity = new File();
        fileEntity.setFileName(newFileName);
        fileEntity.setFilePath(targetLocation.toString());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFileSize(file.getSize());
        fileEntity.setUploadDate(new Date());

        return fileRepo.save(fileEntity);
    }

    public Path loadFileAsResource(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }

    public List<File> getAllFile() {
        return fileRepo.findAll();
    }

    public Optional<File> getFileById(Long id) {
        return fileRepo.findById(id);
    }


    public File updateFile(Long id, File FileDetails) {
        File file = fileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id " + id));

        file.setUploadDate(FileDetails.getUploadDate());
        file.setFileName(FileDetails.getFileName());
        file.setFileSize(FileDetails.getFileSize());
        file.setFilePath(FileDetails.getFilePath());
        file.setFileType(FileDetails.getFileType());
        return fileRepo.save(file);
    }


    public void deleteFile(Long id) throws FileNotFoundException {
        // Retrieve the file entity by its ID
        Optional<File> fileOptional = fileRepo.findById(id);
        if (fileOptional.isPresent()) {
            // Delete the file from the repository
            fileRepo.deleteById(id);

        } else {
            throw new FileNotFoundException("File not found with id: " + id);
        }
    }
}