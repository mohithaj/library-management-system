package com.example.library_management.service;

import com.example.library_management.model.Book;
import com.example.library_management.model.IssueRecord;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.IssueRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<IssueRecord> getAllIssueRecords() { return issueRecordRepository.findAll(); }

    public void issueBook(IssueRecord issueRecord) {
        Book book = issueRecord.getBook();
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
            issueRecord.setIssueDate(LocalDate.now());
            issueRecord.setDueDate(LocalDate.now().plusDays(14));
            issueRecord.setStatus("ISSUED");
            issueRecordRepository.save(issueRecord);
        }
    }

    public IssueRecord getIssueRecordById(Long id) { return issueRecordRepository.findById(id).orElse(null); }

    public void returnBook(Long id) {
        IssueRecord record = issueRecordRepository.findById(id).orElse(null);
        if (record != null) {
            record.setReturnDate(LocalDate.now());
            record.setStatus("RETURNED");
            Book book = record.getBook();
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookRepository.save(book);
            issueRecordRepository.save(record);
        }
    }
}
