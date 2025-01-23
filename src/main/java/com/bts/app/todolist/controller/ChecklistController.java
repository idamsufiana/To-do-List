package com.bts.app.todolist.controller;

import com.bts.app.todolist.dto.ItemDto;
import com.bts.app.todolist.exception.BTSException;
import com.bts.app.todolist.model.CheckList;
import com.bts.app.todolist.model.Item;
import com.bts.app.todolist.service.ChecklistService;
import com.bts.app.todolist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/Checklist")
public class ChecklistController extends BaseController{

    @Autowired
    ChecklistService checklistService;

    @PreAuthorize("hasRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public Object Get(@PathVariable Long id) throws Throwable {
        Optional<CheckList> product = checklistService.get(id);
        if(product.isPresent()){
            return success(checklistService.get(id).get());
        }else {
            throw new BTSException("no data found");
        }

    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping("/update/{id}")
    public Object Update(@PathVariable Long id, @RequestBody ItemDto dto) throws Throwable {
        return success(checklistService.update(id, dto));

    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping("/add")
    public Object add(@RequestBody ItemDto dto) throws Throwable {
        return success(checklistService.createFromDto(dto));
    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @DeleteMapping("/delete/{id}")
    public Object add(@PathVariable Long id) throws Throwable {
        return success(checklistService.delete(id));
    }


}
