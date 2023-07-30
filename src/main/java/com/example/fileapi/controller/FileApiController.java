package com.example.fileapi.controller;

import com.example.fileapi.dto.BaseReturn;
import com.example.fileapi.entity.Document;
import com.example.fileapi.enums.ResponseMessage;
import com.example.fileapi.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/file-api")
public class FileApiController {

    final DocumentService documentService;

    public FileApiController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(value = "/save" )
    public ResponseEntity<BaseReturn<String>> save(@RequestBody MultipartFile file) throws IOException {
        BaseReturn<String> response = new BaseReturn<>();

        if(!documentService.checkFileExtension(file)){
            response.setCode(ResponseMessage.FILE_EXTENSION_NOT_ALLOWED.getCode());
            response.setMessage(ResponseMessage.FILE_EXTENSION_NOT_ALLOWED.getMessage());
        }else if(file.getSize() > 5242880){
            response.setCode(ResponseMessage.FILE_SIZE_ERROR.getCode());
            response.setMessage(ResponseMessage.FILE_SIZE_ERROR.getMessage());
        }
        else{

            try {
                Document doc = documentService.save(file);
                if (doc != null){
                    response.setCode(ResponseMessage.SUCCESS_SAVE.getCode());
                    response.setMessage(ResponseMessage.SUCCESS_SAVE.getMessage());
                }
            }catch (Exception e){
                e.printStackTrace();
                response.setCode(ResponseMessage.ERROR_SAVE.getCode());
                response.setMessage(ResponseMessage.ERROR_SAVE.getMessage());
            }
        }
        return new ResponseEntity<BaseReturn<String>>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<BaseReturn<List<Document>>> getAll(){
        BaseReturn<List<Document>> response = new BaseReturn<>();

        try {
            List<Document> doc = documentService.getAll();
            if (doc.size() > 0){
                response.setCode(ResponseMessage.SUCCESS.getCode());
                response.setMessage(ResponseMessage.SUCCESS.getMessage());
                response.setData(doc);
            }else{
                response.setCode(ResponseMessage.EMPTY_LIST_WARNING.getCode());
                response.setMessage(ResponseMessage.EMPTY_LIST_WARNING.getMessage());
            }
        }catch (Exception e){
            response.setCode(ResponseMessage.GENERIC_ERROR.getCode());
            response.setMessage(ResponseMessage.GENERIC_ERROR.getMessage());
        }

        return new ResponseEntity<BaseReturn<List<Document>>>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<BaseReturn<Document>> update(@RequestBody MultipartFile file, String uuid){
        BaseReturn<Document> response = new BaseReturn<>();

        try {
            boolean check = documentService.update(uuid, file);
            if (check){
                response.setCode(ResponseMessage.SUCCESS_UPDATE.getCode());
                response.setMessage(ResponseMessage.SUCCESS_UPDATE.getMessage());
            }else{
                response.setCode(ResponseMessage.FILE_NOT_FOUND.getCode());
                response.setMessage(ResponseMessage.FILE_NOT_FOUND.getMessage());
            }
        }catch (Exception e){
            response.setCode(ResponseMessage.ERROR_UPDATE.getCode());
            response.setMessage(ResponseMessage.ERROR_UPDATE.getMessage());
        }

        return new ResponseEntity<BaseReturn<Document>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseReturn<Integer>> delete(@RequestParam(value = "uuid") String uuid){
        BaseReturn<Integer> response = new BaseReturn<>();
        try {
            boolean check = documentService.delete(uuid);
            if (check){
                response.setCode(ResponseMessage.SUCCESS_DELETE.getCode());
                response.setMessage(ResponseMessage.SUCCESS_DELETE.getMessage());
            }else{
                response.setCode(ResponseMessage.FILE_NOT_FOUND.getCode());
                response.setMessage(ResponseMessage.FILE_NOT_FOUND.getMessage());
            }
        }catch (Exception e){
            response.setCode(ResponseMessage.ERROR_DELETE.getCode());
            response.setMessage(ResponseMessage.ERROR_DELETE.getMessage());
        }
        return new ResponseEntity<BaseReturn<Integer>>(response, HttpStatus.OK);
    }
    @GetMapping("/getFileToByte")
    public ResponseEntity<BaseReturn<String>> getFiletoByte(@RequestParam(value = "uuid") String uuid) throws IOException {
        BaseReturn<String> response = new BaseReturn<>();
         byte[] file = documentService.getFiletoByte(uuid);
         if (file != null){
             response.setData(Arrays.toString(file));
             response.setCode(ResponseMessage.SUCCESS.getCode());
             response.setMessage(ResponseMessage.SUCCESS.getMessage());
         }else{
             response.setCode(ResponseMessage.FILE_NOT_FOUND.getCode());
             response.setMessage(ResponseMessage.FILE_NOT_FOUND.getMessage());
         }

        return new ResponseEntity<BaseReturn<String>>(response, HttpStatus.OK);

    }
 }
