package br.com.macedo.layout.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.macedo.layout.model.Task;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TaskController {

    List<Task> tasks = new ArrayList<Task>();

    @GetMapping("/create")
    public ModelAndView home(){
        ModelAndView md = new ModelAndView("create");
        md.addObject("task", new Task()); //criando objeto vazio pra não dar erro
        return md;
    }

    @PostMapping("/create")
    public String create(Task task){
        if(task.getId() != null){
            //editando
            Task taskFind = tasks.stream().filter(taskItem -> task.getId().equals(taskItem.
            getId())).findFirst().get();
            tasks.set(tasks.indexOf(taskFind), task);
        } else{
            //criando
            task.setId(tasks.size() + 1L);
            tasks.add(task);
        }
        return "redirect:list";
    }

    @GetMapping("/list")
    public ModelAndView list(){
        //retorna atributos pro html
        //mapeia o modelo para o html
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("tasks", tasks);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {

        ModelAndView mv = new ModelAndView("create");
        Task taskFind = tasks.stream().filter(task -> id.equals(task.getId())).findFirst().get() ;
        mv.addObject("task", taskFind);
        return mv;

    }
    
    
}
