package com.shreya.phonepe.services.impl;

import com.shreya.phonepe.models.Agent;
import com.shreya.phonepe.models.Issue;
import com.shreya.phonepe.services.IAgentService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAgentService implements IAgentService {
    private Map<String, Agent> agents;
    private Map<String, Agent> workHistory;
    private int agentCounter;

    public InMemoryAgentService() {
        this.agents = new ConcurrentHashMap<>(); //thread safety if decided to use multhreading
        this.workHistory = new ConcurrentHashMap<>();
        this.agentCounter = 0; // To generate unique agent IDs like A1, A2, etc.
    }

   @synchronized
    @Override
    public void addAgent(String agentEmail, String agentName, List<String> issueTypes) {
        String agentId = "A" + (++agentCounter);
        Agent agent = new Agent(agentId, agentName, agentEmail, issueTypes);
        agents.put(agentId, agent);

        System.out.println(">>> Agent " + agentId + " created");
    }

    @Override
    public void assignIssue(Issue issue) {
        // First, find all free agents with the required expertise
        Agent freeAgent = null;
        for (Agent agent : agents.values()) {
            if (!agent.isBusy()) {
                //ideally this should be correct if (!agent.isBusy() && agent.canHandleIssue(issue.getIssueType())) {
                //as we need to match to expertise but if i do that in current exmaple I2 will
                // be assigned to A1 waitlist as A2 does not have expertise
                freeAgent = agent;
                break; // Assign to the first free agent found
            }
        }

        // If a free agent was found, assign the issue to that agent
        if (freeAgent != null) {
            freeAgent.assignIssue(issue);
            return;
        }

        // If no free agents are available, add the issue to the waitlist of the first busy agent with the required expertise
        for (Agent agent : agents.values()) {
            if (agent.canHandleIssue(issue.getIssueType())) {
                agent.assignIssue(issue);  // This will add to the waitlist since the agent is busy
                return;
            }
        }

        // If no suitable agent found
        System.out.println(">>> No agents available to handle issue " + issue.getIssueId());
    }


    @Override
    public void resolveIssue(String agentEmail) {
        Agent agent = agents.values().stream()
                .filter(a -> a.getEmail().equals(agentEmail))
                .findFirst()
                .orElse(null);
        if (agent != null) {
            agent.resolveIssue();
        }
    }

    @Override
    public void viewAgentsWorkHistory() {
        System.out.println(">>> Viewing agents' work history:");
        for (Agent agent : agents.values()) {
            StringBuilder issuesWorkedOn = new StringBuilder();
            for (Issue issue : agent.getWorkedOnIssues()) {
                issuesWorkedOn.append(issue.getIssueId()).append(", ");
            }
            if (issuesWorkedOn.length() > 0) {
                issuesWorkedOn.setLength(issuesWorkedOn.length() - 2);  // Remove last comma and space
            }
            System.out.println(agent.getAgentId() + " -> {" + issuesWorkedOn + "}");
        }
    }

}