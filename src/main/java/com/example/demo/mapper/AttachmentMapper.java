package com.example.demo.mapper;

import com.example.demo.entity.Attachment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AttachmentMapper {
    void create(Attachment attachment);
    void delete(Long id);
    void update(Long id, Attachment u);

    Attachment findById(Long id);
    List<Attachment> findAll();
    Attachment findByName(String name);
}