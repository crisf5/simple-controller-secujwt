package com.cbatech.springsecu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SiteWebController {

    @GetMapping("/")
    public ResponseEntity<String> getMsg(){
        return new ResponseEntity<>("Hola, este es el mensaje sencillo", HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/advanced")
    public ResponseEntity<String> getMsgAdvanced(){
        return new ResponseEntity<>("Mensaje avanzadooo!!", HttpStatus.OK);
    }
}
