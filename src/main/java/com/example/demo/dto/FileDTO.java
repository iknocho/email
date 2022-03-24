package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileDTO {

    List<MultipartFile> files;
    private String field1;
    private String field2;
    private String field3;
    private String field4;

}
