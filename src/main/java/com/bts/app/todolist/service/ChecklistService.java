package com.bts.app.todolist.service;

import com.bts.app.todolist.dto.CheckListDto;
import com.bts.app.todolist.dto.ItemDto;
import com.bts.app.todolist.exception.BTSException;
import com.bts.app.todolist.model.CheckList;
import com.bts.app.todolist.model.Item;
import com.bts.app.todolist.repository.ChecklistRepository;
import com.bts.app.todolist.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ChecklistService {

    @Autowired
    ChecklistRepository checklistRepository;

    public CheckList createFromDto(CheckListDto dto) throws Throwable {
        try {
            CheckList entity = new CheckList();
            BeanUtils.copyProperties(dto, entity);
            entity.setCreatedDate(new Date());
            checklistRepository.save(entity);
            return entity;
        } catch (Throwable var5) {
            Throwable $ex = var5;
            throw $ex;
        }
    }

    public Optional<CheckList> get(Long id) throws Throwable {
        try {
            return checklistRepository.findById(id);
        } catch (Throwable var3) {
            Throwable $ex = var3;
            throw $ex;
        }
    }

    public CheckList update(Long id, CheckListDto dto) throws Throwable {
        try {
            CheckList entity = new CheckList();
            if(get(id).isPresent()){
                entity = checklistRepository.findById(id).get();
                BeanUtils.copyProperties(dto, entity);
                entity.setUpdatedDate(new Date());
                return checklistRepository.save(entity);
            }else{
                throw new BTSException("not found");
            }
        } catch (Throwable var5) {
            Throwable $ex = var5;
            throw $ex;
        }
    }

    public CheckList delete(Long id) throws Throwable {
        try {
            CheckList entity = new CheckList();
            if(get(id).isPresent()) {
                entity = checklistRepository.findById(id).get();
                checklistRepository.delete(entity);
                return entity;
            }else {
                throw new BTSException("data not found");
            }

        } catch (Throwable var3) {
            Throwable $ex = var3;
            throw $ex;
        }
    }
}
