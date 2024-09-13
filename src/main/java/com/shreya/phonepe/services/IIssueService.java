package com.shreya.phonepe.services;
import com.shreya.phonepe.models.Issue;

import java.util.List;
import java.util.Map;

public interface IIssueService {
    Issue createIssue(String transactionId, String issueType, String subject, String description, String email);
    Issue getIssueById(String issueId);
    Map<String, Issue> getIssues();
    void updateIssue(String issueId, String status, String resolution);

    List<Issue> getIssue(Map<String, String> filter);

    void resolveIssue(String issueId, String resolution);
}
