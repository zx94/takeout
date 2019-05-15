package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.entity.ProductCategory;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class MemberService {

    @Autowired
    private MemberMapper mapper;
    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createMember(Member u) {
        u.setId(idWorker.nextId());
        u.setBeenDeleted(false);
        mapper.create(u);
    }
    public void updateMember(Long id,Member u){
        mapper.update(id,u);
    }
    public void deleteMember(Long id){
        mapper.delete(id);
    }

    public List<Member> getAllMembers() {
        return mapper.findAll();
    }
    public Member getById(Long id) {
        return mapper.findById(id);
    }
    public List<Member> getByName(String Name) {
        return mapper.findByName(Name);
    }

}
