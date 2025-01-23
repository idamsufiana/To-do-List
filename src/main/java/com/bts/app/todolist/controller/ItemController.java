package com.bts.app.todolist.controller;

import com.bts.app.todolist.dto.ItemDto;
import com.bts.app.todolist.exception.BTSException;
import com.bts.app.todolist.model.Item;
import com.bts.app.todolist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/Item")
public class ItemController extends BaseController{
    @Autowired
    ItemService itemService;

    @PreAuthorize("hasRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public Object Get(@PathVariable Long id) throws Throwable {
        Optional<Item> product = itemService.get(id);
        if(product.isPresent()){
            return success(itemService.get(id).get());
        }else {
            throw new BTSException("no data found");
        }

    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping("/update/{id}")
    public Object Update(@PathVariable Long id, @RequestBody ItemDto dto) throws Throwable {
        return success(itemService.update(id, dto));

    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping("/add")
    public Object add(@RequestBody ItemDto dto) throws Throwable {
        return success(itemService.createFromDto(dto));
    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @DeleteMapping("/delete/{id}")
    public Object add(@PathVariable Long id) throws Throwable {
        return success(itemService.delete(id));
    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping("/{checklistId}/add")
    public ResponseEntity<?> createChecklistItem(@PathVariable Long checklistId,
                                                 @RequestBody ItemDto itemDto) throws Throwable {
        return success(itemService.createItem(checklistId, itemDto));

    }

}

