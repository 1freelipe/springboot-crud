package br.com.fatecararas.dicasdevspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fatecararas.dicasdevspring.domain.CategoriaEntity;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    
}
