package org.stevens.springbasics;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    List<Tasks> tasksList = new ArrayList<>();
    private int nextTaskId = 1;
    @GetMapping("")
    List<Tasks> getAllTasks(){
        return tasksList;
    }

    @PostMapping("/create")
    Tasks createTask(@RequestBody Tasks tasks){
        tasks.setId(nextTaskId++);
        tasksList.add(tasks);
        return tasks;
    }

    @GetMapping("/{id}")
    Tasks getTasks(@PathVariable("id") Integer id){
        var foundTask = tasksList.stream()
                .filter(tasks -> tasks.getId().equals(id))
                .findFirst()
                .orElse(null);
        return foundTask;
    }
}
