package com.example.backRadiology.controller.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ApiController {

    // guardar archivos
    /*
     * @PostMapping("/uploadFile")
     * public ResponseEntity<Object> uploadFile(@RequestParam(value = "file")
     * MultipartFile file) throws IOException {
     * if(!file.isEmpty()){
     * try {
     * File convertFile = new File( ".//src//main//resources//files//" +
     * file.getOriginalFilename());
     * convertFile.createNewFile();
     * FileOutputStream fout = new FileOutputStream(convertFile);
     * fout.write(file.getBytes());
     * fout.close();
     * return new ResponseEntity<Object>("The File Uploaded Successfully.",
     * HttpStatus.OK);
     * } catch (Exception e) {
     * return new ResponseEntity<Object>("You failed to upload." + e.getMessage(),
     * HttpStatus.BAD_REQUEST);
     * }
     * } else {
     * return new ResponseEntity<Object>("No uploaded file found.",
     * HttpStatus.NOT_FOUND);
     * }
     * }
     */
    
    @Secured({ "ROLE_ADMIN", "ROLE_USER, ROLE_PUBLICATOR" })
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        String respuesta = "";

        ResponseEntity<String> response;
        Map<String, Object> response1 = new HashMap<String, Object>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            // String url = "http://172.31.84.1:5000/file-upload";
            String url = "http://127.0.0.1:5000/radiografia";
            HttpMethod requestMethod = HttpMethod.POST;
            // HttpMethod requestMethod1 = HttpMethod.GET;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Convertir multipart file a file
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            ////////////////////////////////////////

            MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
            ContentDisposition contentDisposition = ContentDisposition
                    .builder("form-data")
                    .name("file")
                    .filename(convFile.getName())
                    .build();

            fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
            HttpEntity<byte[]> fileEntity = new HttpEntity<>(Files.readAllBytes(convFile.toPath()), fileMap);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", fileEntity);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            response = restTemplate.exchange(url, requestMethod, requestEntity, String.class);
            respuesta = response.toString();

            // System.out.print(respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }

         response1.put("prediccion", respuesta);
        //response1.put("Prediccion", respuesta.substring(40, respuesta.length()-160));

        return new ResponseEntity<Map<String, Object>>(response1, HttpStatus.ACCEPTED);
    }

}
