package com.bts.app.todolist.service;

import com.bts.app.todolist.dto.ItemDto;
import com.bts.app.todolist.exception.BTSException;
import com.bts.app.todolist.model.Item;
import com.bts.app.todolist.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item createFromDto( ItemDto dto) throws Throwable {
        try {
            Item entity = new Item();
            BeanUtils.copyProperties(dto, entity);
            entity.setCreatedDate(new Date());
            itemRepository.save(entity);
            return entity;
        } catch (Throwable var5) {
            Throwable $ex = var5;
            throw $ex;
        }
    }

    public Optional<Item> get(Long id) throws Throwable {
        try {
            return itemRepository.findById(id);
        } catch (Throwable var3) {
            Throwable $ex = var3;
            throw $ex;
        }
    }

    public Item update(Long id, ItemDto dto) throws Throwable {
        try {
            Item entity = new Item();
            if(get(id).isPresent()){
                entity = itemRepository.findById(id).get();
                BeanUtils.copyProperties(dto, entity);
                entity.setUpdatedDate(new Date());
                return itemRepository.save(entity);
            }else{
                throw new BTSException("not found");
            }
        } catch (Throwable var5) {
            Throwable $ex = var5;
            throw $ex;
        }
    }

    public Item delete(Long id) throws Throwable {
        try {
            Item entity = new Item();
            if(get(id).isPresent()) {
                entity = itemRepository.findById(id).get();
                itemRepository.delete(entity);
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
