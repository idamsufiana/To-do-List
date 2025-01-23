package com.bts.app.todolist.controller;

import com.bts.app.todolist.dto.CheckListDto;
import com.bts.app.todolist.dto.ItemDto;
import com.bts.app.todolist.exception.BTSException;
import com.bts.app.todolist.model.CheckList;
import com.bts.app.todolist.model.Item;
import com.bts.app.todolist.service.ChecklistService;
import com.bts.app.todolist.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
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
    public Object Update(@PathVariable Long id, @RequestBody CheckListDto dto) throws Throwable {
        return success(checklistService.update(id, dto));

    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping("/add")
    public Object add(@RequestBody CheckListDto dto) throws Throwable {
        return success(checklistService.createFromDto(dto));
    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @DeleteMapping("/delete/{id}")
    public Object add(@PathVariable Long id) throws Throwable {
        return success(checklistService.delete(id));
    }



    @PreAuthorize("hasRole('USER','ADMIN')")
    @GetMapping({"list"})
    public ResponseEntity<Object> list(@RequestParam(required = false,defaultValue = "0") int page, @RequestParam(required = false,defaultValue = "100") int limit, @RequestParam(required = false) String sort, @RequestParam(required = false,defaultValue = "true") boolean asc) throws Throwable {
        Pageable pageable = this.pageFromRequest(page, limit, sort, asc);
        return this.success(this.checklistService.findAll(pageable));
    }

    @DeleteMapping("/{checklistId}/items/{itemId}")
    public ResponseEntity<?> deleteItemFromChecklist(
            @PathVariable Long checklistId,
            @PathVariable Long itemId) {
        try {
            checklistService.deleteItem(checklistId, itemId);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Item deleted successfully"));
        } catch (EntityNotFoundException e) {
            return error(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PatchMapping("/{checklistId}/items/{itemId}/status")
    public ResponseEntity<?> updateItemStatus(
            @PathVariable Long checklistId,
            @PathVariable Long itemId,
            @RequestBody String status) {
        try {
            Item updatedItem = checklistService.updateItemStatus(checklistId, itemId, status);
            return ResponseEntity.ok().body(updatedItem);
        } catch (EntityNotFoundException e) {
            return error(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            return error(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
