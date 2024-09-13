package com.shreya.phonepe.services.impl;
import com.shreya.phonepe.models.Issue;
import com.shreya.phonepe.services.IIssueService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryIssueService implements IIssueService {
    private Map<String, Issue> issues;
    private int issueCounter;

    public InMemoryIssueService() {
        this.issues = new ConcurrentHashMap<>();
        this.issueCounter = 0;
    }

    @Override
    public Issue createIssue(String transactionId, String issueType, String subject, String description, String email) {
        String issueId = "I" + (++issueCounter);
        Issue issue = new Issue(issueId, transactionId, issueType, subject, description, email);
        issues.put(issueId, issue);
        System.out.printf(">>> Issue %s created against transaction \"%s\"%n", issueId, transactionId);
        return issue;
    }

    @Override
    public Issue getIssueById(String issueId) {
        return issues.get(issueId);
    }

    @Override
    public Map<String, Issue> getIssues() {
        // to fulfill the interface contract
        return issues;
    }

    @Override
    public List<Issue> getIssue(Map<String, String> filter) {
        List<Issue> filteredIssues = new ArrayList<>();

        if (filter.containsKey("email")) {
            String email = filter.get("email");
            for (Issue issue : issues.values()) {
                if (issue.getEmail().equals(email)) {
                    filteredIssues.add(issue);
                }
            }
        } else if (filter.containsKey("type")) {
            String issueType = filter.get("type");
            for (Issue issue : issues.values()) {
                if (issue.getIssueType().equalsIgnoreCase(issueType)) {
                    filteredIssues.add(issue);
                }
            }
        }

        return filteredIssues;
    }


    @Override
    public void updateIssue(String issueId, String status, String resolution) {
        Issue issue = issues.get(issueId);
        if (issue != null) {
            issue.setStatus(status);
            issue.setResolution(resolution);
            System.out.println(">>> Issue " + issueId + " status updated to " + status);
        }
    }

    @Override
    public void resolveIssue(String issueId, String resolution) {
        Issue issue = issues.get(issueId);
        if (issue != null) {
            issue.setStatus("Resolved");
            issue.setResolution(resolution);
            System.out.println(">>> Issue " + issueId + " issue marked resolved");
        } else {
            System.out.println(">>> Issue " + issueId + " not found.");
        }
    }


}
