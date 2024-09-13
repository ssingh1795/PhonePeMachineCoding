package com.shreya.phonepe;

import com.shreya.phonepe.models.Issue;
import com.shreya.phonepe.services.*;
import com.shreya.phonepe.services.impl.SimpleAssignmentStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        IIssueService issueService = ServiceFactory.createIssueService();
        IAgentService agentService = ServiceFactory.createAgentService();

        // Create issues with consistent types that match agent expertise
        Issue i1 = issueService.createIssue("T1", "Payment Related", "Payment Failed", "Payment Failed but debited", "user1@test.com");
        Issue i2 = issueService.createIssue("T2", "Gold Related", "Purchase Failed", "Failed to purchase gold", "user2@test.com");
        Issue i3 = issueService.createIssue("T3", "Payment Related", "Payment Failed", "My payment failed but money is debited", "testUser2@test.com");

        // Add agents with expertise in issue types
        agentService.addAgent("agent1@test.com", "Agent 1", Arrays.asList("Payment Related", "Gold Related"));
        agentService.addAgent("agent2@test.com", "Agent 2", Arrays.asList("Payment Related"));

        // Assign issues to available agents
        agentService.assignIssue(i1);  // A1
        agentService.assignIssue(i2);  // A2
        agentService.assignIssue(i3); // A1

        List<Issue> issuesByEmail = issueService.getIssue(Map.of("email", "testUser2@test.com"));
        for (Issue issue : issuesByEmail) {
            System.out.println(issue);
        }

        // Filter issues by type
        List<Issue> issuesByType = issueService.getIssue(Map.of("type", "Payment Related"));
        for (Issue issue : issuesByType) {
            System.out.println(issue);
        }

        issueService.updateIssue("I3", "In Progress", "Waiting for payment confirmation");

        //issueService.updateIssue("I3", "In Progress", "Waiting for payment confirmation");


        // Test resolveIssue
        issueService.resolveIssue("I3", "Payment failed, debited amount will be reversed");


        agentService.viewAgentsWorkHistory();
    }
}