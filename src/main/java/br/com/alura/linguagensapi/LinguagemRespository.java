package br.com.alura.linguagensapi;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinguagemRespository extends MongoRepository<Linguagem, String>{
     
    List<Linguagem>findByOrderByRanking();

}
