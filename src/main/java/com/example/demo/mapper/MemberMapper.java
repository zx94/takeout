package com.example.demo.mapper;

import com.example.demo.entity.Member;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MemberMapper {
    void create(Member pc);
    void delete(Long id);
    void update(Long id, Member u);

    List<Member> findAll();
    Member findById(Long id);
    List<Member> findByName(String name);
}