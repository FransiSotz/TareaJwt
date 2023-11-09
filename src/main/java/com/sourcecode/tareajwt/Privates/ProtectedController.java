package com.sourcecode.tareajwt.Privates;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProtectedController {
    @PostMapping(value = "demo")
    public String bienvenida() {
        return "Saludos desde el endPoint Autenticado";
    }
}
