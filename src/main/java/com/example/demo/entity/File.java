package com.example.demo.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFile;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private long fileSize;

    @Column(nullable = false)
    private Date uploadDate = new Date();


    @ManyToMany(mappedBy = "files")
    private List<Reclamation> reclamations;


@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof File file)) return false;
    return fileSize == file.fileSize && Objects.equals(idFile, file.idFile) && Objects.equals(fileName, file.fileName) && Objects.equals(filePath, file.filePath) && Objects.equals(fileType, file.fileType) && Objects.equals(uploadDate, file.uploadDate) && Objects.equals(reclamations, file.reclamations);
}

    @Override
    public int hashCode() {
        return Objects.hash(idFile, fileName, filePath, fileType, fileSize, uploadDate, reclamations);
    }
    public Long getId() {
        return idFile;
    }

    public void setId(Long id) {
        this.idFile = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
