package br.com.fatecararas.dicasdevspring.resources;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecararas.dicasdevspring.domain.CategoriaEntity;
import br.com.fatecararas.dicasdevspring.dto.CategoriaDTO;
import br.com.fatecararas.dicasdevspring.repositories.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {

    private final CategoriaRepository repository;

    private final ModelMapper modelMapper;

    public CategoriaResource(CategoriaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaEntity> categories = repository.findAll();
        if(categories.isEmpty()) return ResponseEntity.notFound().build();
        List<CategoriaDTO> categories = modelMapper.map(categories, listType);
        return ResponseEntity.ok(categories);
    }
}
