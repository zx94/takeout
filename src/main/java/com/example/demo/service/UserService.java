package com.example.demo.service;

import com.example.demo.entity.AuthorityEnum;
import com.example.demo.entity.Member;
import com.example.demo.entity.Seller;
import com.example.demo.entity.User;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private SnowflakeIdWorker idWorker;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private MemberService memberService;

    public void createUser(User u) {
        u.setId(idWorker.nextId());

        if(u.getAuthorityName()== AuthorityEnum.Seller.getValue()){
            Seller seller=new Seller();
            seller.setLoginAccount(u.getUserName());
            sellerService.createSeller(seller);
        }

        if(u.getAuthorityName()== AuthorityEnum.Member.getValue()){
            Member member=new Member();
            member.setName(u.getUserName());
            memberService.createMember(member);
        }

        mapper.create(u);
    }
    public void updateUser(Long id,User u){
        mapper.update(id,u);
    }

    public void activeUser(Long id){
        User u = mapper.findById(id);
        u.setIsValid(true);
        mapper.update(id,u);
    }
    public void deleteUser(Long id){
        mapper.delete(id);
    }

    public List<User> getAllUsers() {
        return mapper.findAll();
    }
    public User getById(Long id) {
        return mapper.findById(id);
    }
    public User getByUserName(String userName) {
        return mapper.findByUserName(userName);
    }

}
