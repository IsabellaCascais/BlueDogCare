package com.bluedogcare.gerenciamento.repository;

import com.bluedogcare.gerenciamento.model.Tutor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    
    List<Tutor> findByNomeContaining(String nome);
    
}
