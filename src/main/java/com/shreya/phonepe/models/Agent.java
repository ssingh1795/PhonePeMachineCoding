package com.shreya.phonepe.models;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Agent {
    private String agentId;  // Unique ID like A1, A2
    private String email;    // Email of the agent
    private String name;
    private List<String> issueTypes;  // The issue types this agent can handle
    private Issue currentIssue;
    private List<Issue> workedOnIssues;
    private boolean busy;
    private Queue<Issue> waitlist;

    public Agent(String agentId, String name, String email, List<String> issueTypes) {
        this.agentId = agentId;
        this.name = name;
        this.email = email;
        this.issueTypes = issueTypes;
        this.workedOnIssues = new ArrayList<>();
        this.waitlist = new LinkedList<>();
        this.busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    public String getEmail() {
        return email;
    }


    public boolean canHandleIssue(String issueType) {
        return issueTypes.stream()
                .anyMatch(type -> type.equalsIgnoreCase(issueType));  // Case-insensitive match
    }

    public String getAgentId() {
        return agentId;
    }


    public void assignIssue(Issue issue) {
        if (!busy) {
            this.currentIssue = issue;
            this.busy = true;
            workedOnIssues.add(issue);  // Add to work history
            System.out.println(">>> Issue " + issue.getIssueId() + " assigned to agent " + agentId);
        } else {
            waitlist.add(issue);  // Add to waitlist if the agent is busy
            System.out.println(">>> Issue " + issue.getIssueId() + " added to waitlist of Agent " + agentId);
        }
    }


    public void resolveIssue() {
        if (this.currentIssue != null) {
            this.currentIssue.setStatus("Resolved");
            this.busy = false;
            System.out.println(">>> Issue " + currentIssue.getIssueId() + " resolved by agent " + agentId);
            if (!waitlist.isEmpty()) {
                assignIssue(waitlist.poll());  // Assign next issue from the waitlist
            }
        }
    }


    public List<Issue> getWorkedOnIssues() {
        List<Issue> allIssues = new ArrayList<>(workedOnIssues);  // Start with all worked on issues
        allIssues.addAll(waitlist);  // Add all waitlisted issues
        return allIssues;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "agentId='" + agentId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", issueTypes=" + issueTypes +
                '}';
    }
}