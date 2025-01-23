package com.bts.app.todolist.repository;

import com.bts.app.todolist.model.CheckList;
import com.bts.app.todolist.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByIdAndChecklist(Long id, CheckList checklist);
}
