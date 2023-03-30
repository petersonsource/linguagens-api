package br.com.alura.linguagensapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LinguagemController {
    
    @Autowired
    private LinguagemRespository repository;

    @GetMapping(value = "/linguagem-preferida")
    public String getMethodName(){
        return "Oi, java!";
    }    

    @GetMapping(value = "/linguagens")
    public List obterLinguagens(){
        List<Linguagem> linguagens = repository.findByOrderByRanking();
        return linguagens;
    }
    
    @PostMapping(value = "/linguagens")
    public ResponseEntity cadastrarLinguagem(@RequestBody Linguagem linguagem){
        Linguagem linguagemSalva = repository.save(linguagem);
        return new ResponseEntity<>(linguagem, HttpStatus.CREATED);
    }
    
    @GetMapping(value="/linguagens/{id}")
    public Linguagem obterLinguagemPorId(@PathVariable String id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value="/linguagens/{id}")
    public Linguagem atualizaLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem) {
        if(!repository.existsById(id)) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND));
        }
        linguagem.setId(id);
        Linguagem linguagemSalva = repository.save(linguagem);
        return linguagem;
    };
 
    @DeleteMapping(value="/linguagens/{id}")
    public void deletaLinguagem(@PathVariable String id) {
        if(!repository.existsById(id)) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND));
        }
        repository.deleteById(id);
    }


}
