package br.com.macedo.layout.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.macedo.layout.model.Task;
import br.com.macedo.layout.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(Task addTask) {
        addTask.setId(null);
        Task savedTask = taskRepository.save(addTask);
        return savedTask;
    }

    @Override
    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task update(Task updateTask, Long taskID) {
        if(taskRepository.existsById(taskID))
            return taskRepository.save(updateTask);
        return null;
    }

    @Override
    public Optional<Task> findTaskById(Long taskID) {
        return taskRepository.findById(taskID);
    }

    @Override
    public void delete(Long id) {
        if(taskRepository.existsById(id))
            taskRepository.deleteById(id);
    }

    

    
}
