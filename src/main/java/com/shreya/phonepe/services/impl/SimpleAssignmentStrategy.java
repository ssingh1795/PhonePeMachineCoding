package com.shreya.phonepe.services.impl;

import com.shreya.phonepe.models.Agent;
import com.shreya.phonepe.models.Issue;
import com.shreya.phonepe.services.IAssignmentStrategy;

import java.util.List;

public class SimpleAssignmentStrategy implements IAssignmentStrategy {
    @Override
    public Agent assign(List<Agent> availableAgents, Issue issue) {
        for (Agent agent : availableAgents) {
            if (!agent.isBusy()) {
                return agent;
            }
        }
        return null;
    }
}
