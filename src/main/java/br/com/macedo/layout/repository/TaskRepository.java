package br.com.macedo.layout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.macedo.layout.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    
}
