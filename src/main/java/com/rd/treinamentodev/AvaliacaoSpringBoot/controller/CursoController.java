package com.rd.treinamentodev.AvaliacaoSpringBoot.controller;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.CursoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.CursoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("/curso")
    public ResponseEntity gravar(@RequestBody CursoDTO cursoDTO){
        //return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.gravar(cursoDTO));
        return cursoService.gravar(cursoDTO);
    }

    @GetMapping("/curso")
    public ResponseEntity getCursos() {

        List<CursoEntity> cursos = cursoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(cursos);

    }

    @GetMapping("/curso-buscar/{nomeCurso}")
    public ResponseEntity buscarCursoPorNome(@PathVariable("nomeCurso") String nomeCurso) {

        List<CursoEntity> c = cursoService.consultarPorNome(nomeCurso);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }
}
