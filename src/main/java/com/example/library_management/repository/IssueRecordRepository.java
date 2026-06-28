package com.example.library_management.repository;

import com.example.library_management.model.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {
    List<IssueRecord> findByStatus(String status);
}
