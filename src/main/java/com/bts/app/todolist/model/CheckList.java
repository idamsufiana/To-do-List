package com.bts.app.todolist.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CheckList extends CrudEntity{
    private String title;
    private Integer userId;
}
