package br.com.macedo.layout.service;

import java.util.List;
import java.util.Optional;

import br.com.macedo.layout.model.Task;

public interface ITaskService {
        public Task createTask(Task addTask);

        public List<Task> listAllTasks();

        public Task update(Task updateTask, Long taskID);

        public Optional<Task> findTaskById(Long taskID);

        public void delete(Long id);


}
