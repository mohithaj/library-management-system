package com.example.library_management.service;

import com.example.library_management.model.Member;
import com.example.library_management.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() { return memberRepository.findAll(); }

    public void saveMember(Member member) { memberRepository.save(member); }

    public Member getMemberById(Long id) { return memberRepository.findById(id).orElse(null); }

    public void deleteMember(Long id) { memberRepository.deleteById(id); }
}
