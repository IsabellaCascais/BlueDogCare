package com.bluedogcare.gerenciamento.repository;

import com.bluedogcare.gerenciamento.model.Cachorro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Integer> {
    
    List<Cachorro> findByNomeContaining(String nome);
}
