package com.bts.app.todolist.repository;

import com.bts.app.todolist.model.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<CheckList, Long> {
}
