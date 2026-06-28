package com.example.library_management.controller;

import com.example.library_management.model.IssueRecord;
import com.example.library_management.service.BookService;
import com.example.library_management.service.IssueService;
import com.example.library_management.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/issues")
public class IssueController {

    @Autowired private IssueService issueService;
    @Autowired private BookService bookService;
    @Autowired private MemberService memberService;

    @GetMapping
    public String getAllIssues(Model model) {
        model.addAttribute("issues", issueService.getAllIssueRecords());
        return "issues/list";
    }

    @GetMapping("/issue")
    public String showIssueForm(Model model) {
        model.addAttribute("issueRecord", new IssueRecord());
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("members", memberService.getAllMembers());
        return "issues/issue";
    }

    @PostMapping("/issue")
    public String issueBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        IssueRecord record = new IssueRecord();
        record.setBook(bookService.getBookById(bookId));
        record.setMember(memberService.getMemberById(memberId));
        issueService.issueBook(record);
        return "redirect:/issues";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        issueService.returnBook(id);
        return "redirect:/issues";
    }
}
