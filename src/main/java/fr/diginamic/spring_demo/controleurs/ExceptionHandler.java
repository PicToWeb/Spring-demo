package fr.diginamic.spring_demo.controleurs;

import fr.diginamic.spring_demo.utilitaire.AnomalieException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler  {

    @org.springframework.web.bind.annotation.ExceptionHandler({AnomalieException.class})
    protected ResponseEntity<String>traiterErreurs(AnomalieException anomalieException) {
        return ResponseEntity.badRequest().body(anomalieException.getMessage());
    }
}