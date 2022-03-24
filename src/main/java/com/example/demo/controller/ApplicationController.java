package com.example.demo.controller;

import com.example.demo.dto.FileDTO;
import com.example.demo.dto.applicationDTO;
import com.example.demo.model.applicationEntity;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.example.demo.service.applicationService;
@Slf4j
@RestController
@Controller
public class ApplicationController {
    @Autowired
    private applicationService service;
    @Autowired
    private JavaMailSender javaMailSender;



    String filePath="/Users/ikno/testspring";

    public static void printList(File file){
        File[] fList=file.listFiles();
        for(File f:fList){
            System.out.println(f.getAbsolutePath());
            if(f.listFiles()!=null)
                printList(f);
        }
    }


    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> uploadApplication( applicationDTO dto) throws IOException, MessagingException {

        List<String> list=new ArrayList<>();
        List<MultipartFile> files=dto.getFiles();
        //MultipartFile file=fileDTO.getFiles();
        String companyName=dto.getCompanyName();
        String brandName=dto.getBrandName();
        String companyNumber=dto.getCompanyNumber();
        String detailPage=dto.getDetailPage();
        applicationEntity entity=applicationDTO.toEntity(dto);
        service.upload(entity);
        service.sendCustomer(entity);
        service.sendManager(entity,files);

        log.info("companyName:{}",companyName);
        log.info("brandName:{}",brandName);
        log.info("companyNumber:{}",companyNumber);
        log.info("detailPage:{}",detailPage);

        return list;
    }


}

