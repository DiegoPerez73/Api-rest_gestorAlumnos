package com.diego.appCurso.controller;

import com.diego.appCurso.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

@GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("Soy el admin");
    }

}
