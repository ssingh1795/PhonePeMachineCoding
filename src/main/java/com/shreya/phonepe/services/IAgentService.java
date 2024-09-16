package com.shreya.phonepe.services;

import com.shreya.phonepe.models.Agent;
import com.shreya.phonepe.models.Issue;

import java.util.List;
import java.util.Map;

public interface IAgentService {
    void addAgent(String agentEmail, String agentName, List<String> issueTypes);

    /**
     * Assigns an issue to the first available agent who can handle the issue based on their expertise.
     * @param issue The issue to be assigned.
     */
    void  assignIssue(Issue issue);

    /**
     * Marks the current issue for the given agent as resolved.
     * @param agentEmail The email of the agent.
     */
    void resolveIssue(String agentEmail);

    /**
     * Returns the work history of the agents.
     * @return A map of agent emails to the agents' details including their work history.
     */
    void viewAgentsWorkHistory();
}
