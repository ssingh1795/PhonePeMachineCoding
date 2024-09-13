package com.shreya.phonepe.models;

public class Issue {
    private String issueId;
    private String transactionId;
    private String issueType;
    private String subject;
    private String description;
    private String email;
    private String status;
    private String resolution;

    public Issue(String issueId, String transactionId, String issueType, String subject, String description, String email) {
        this.issueId = issueId;
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.status = "Open";
    }

    public String getIssueId() {
        return issueId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getEmail() {
        return email;
    }

    public String getIssueType() {
        return issueType;
    }

    @Override
    public String toString() {
        return String.format("%s{\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\"}",
                issueId, transactionId, issueType, subject, description, email, status);
    }
}

