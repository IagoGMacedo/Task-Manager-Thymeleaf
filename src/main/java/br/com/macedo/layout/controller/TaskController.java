package br.com.macedo.layout.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.macedo.layout.model.Task;
import br.com.macedo.layout.service.ITaskService;
import br.com.macedo.layout.service.TaskService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class TaskController {

    List<Task> tasks = new ArrayList<Task>();
    private final ITaskService taskService;

    @GetMapping("/")
    public String redirectToPage() {
        return "redirect:list";
    }


    @GetMapping("/create")
    public ModelAndView home(){
        ModelAndView md = new ModelAndView("create");
        md.addObject("task", new Task()); //criando objeto vazio pra não dar erro
        md.addObject("message", new String()); //criando objeto vazio pra não dar erro
        
        return md;
    }

    @PostMapping("/create")
    public String create(Task task){
        if(task.getId() != null)
            taskService.update(task, task.getId());
        else
            taskService.createTask(task);
        return "redirect:list";
    }

    @GetMapping("/list")
    public ModelAndView list(){
        //retorna atributos pro html
        //mapeia o modelo para o html
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("tasks", taskService.listAllTasks());
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Optional<Task> findTask = taskService.findTaskById(id);
        if(findTask.isPresent()){
            ModelAndView mv = new ModelAndView("create");
            mv.addObject("task", findTask.get());
            return mv;
        } 
        return new ModelAndView("list");

    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        taskService.delete(id);
        return new ModelAndView("list");
    }
    
    
}
