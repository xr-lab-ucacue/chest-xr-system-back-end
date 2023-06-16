package com.example.backRadiology.controller.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.backRadiology.model.Prediction;



@RestController
@RequestMapping("/api")
public class ApiController {

    @Secured({ "ROLE_ADMIN", "ROLE_USER", "ROLE_PUBLICATOR" })
    @PostMapping("/upload")
    public ResponseEntity<?> upload3(@RequestParam(value = "file") MultipartFile file) throws Exception {

        String flaskServiceUrl = "http://127.0.0.1:5000/radiografia";

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected for uploading");
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpMethod requestMethod = HttpMethod.POST;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Convertir multipart file a file
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();

            // Contruimos el cuerpo de la solicitud
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
            
            // Realizar la llamada al servicio Flask
            //ResponseEntity<Object[]> response = restTemplate.exchange(flaskServiceUrl, requestMethod, requestEntity, Object[].class);
            // Obtener el array de objetos de la respuesta
            //Object[] objects = response.getBody();

            // Realizar la llamada al servicio Flask
            ResponseEntity<Prediction[]> response = restTemplate.exchange(flaskServiceUrl, requestMethod, requestEntity, Prediction[].class);
            // Obtener el array de objetos de la respuesta
            Prediction[] objects = response.getBody();

            // Retornamos los objetos recibidos
            return new ResponseEntity<>(objects, HttpStatus.OK);
           
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Prediction server is down", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
