package br.com.fatecararas.dicasdevspring.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fatecararas.dicasdevspring.domain.DicasDevEntity;
import br.com.fatecararas.dicasdevspring.repositories.DicasDevRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dicasdev")
public class DicasDevController {

    @Autowired
    private DicasDevRepository repository; // Instanciando o repository já com injeção de dependências com o autowired
    
    @PostMapping("/criar")
    public ResponseEntity<DicasDevEntity> create(@RequestBody DicasDevEntity dicasDevEntity) {
        DicasDevEntity entity = repository.save(dicasDevEntity);
        try {
            URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(entity.getId())
            .toUri(); // Utilizado para fornecer exatamente o caminho que foi criado a instancia no banco

        // Devolvendo a URI criada no banco com o created que devolve o código 201, HTTP reponse correto para devolver ao usuário.
        return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        // Blindando caso o ID não exista
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Retornando a resposta caso o ID exista
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DicasDevEntity> find(@PathVariable Integer id) {
        // Buscando o ID no banco mas caso ele não exista, lançando uma mensagem no console
        DicasDevEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dica não encontrada"));

        return ResponseEntity.ok().body(entity);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DicasDevEntity> edit(@PathVariable Integer id, @RequestBody DicasDevEntity dicasDevEntity) {
        // Entrando no try e capturando o ID que está vindo nos parâmetro
        try {
            DicasDevEntity updateEntity = repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ID não encontrado"));

            // Setando os novos atributos na instancia mas capturando os valores que estão vindo no body da requisição
            updateEntity.setTitulo(dicasDevEntity.getTitulo());
            updateEntity.setDescricao(dicasDevEntity.getDescricao());
            repository.save(updateEntity);
    
            // Retornando a resposta
            return ResponseEntity.ok().body(updateEntity);
        } catch (RuntimeException e) {
            // Capturando o Runtime se for lançado e logando a mensagem
            System.err.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    // findAll do repository já retorna uma lista com todos os usuários cadastrados no banco
    public ResponseEntity<List<DicasDevEntity>> getAll() {
       List<DicasDevEntity> list = repository.findAll();

       return ResponseEntity.ok().body(list);
    }
}
