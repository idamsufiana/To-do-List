package com.bts.app.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item extends CrudEntity{
    private String name;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "checklist_id", nullable = false)
    private CheckList checkList;
}
