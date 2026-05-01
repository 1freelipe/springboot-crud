package br.com.fatecararas.dicasdevspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fatecararas.dicasdevspring.domain.DicasDevEntity;

// Indicando que essa interface é um repository já extendendo da classe JPA, classe essa que mexe com o ORM
@Repository
public interface DicasDevRepository extends JpaRepository<DicasDevEntity, Integer>{
    
}
