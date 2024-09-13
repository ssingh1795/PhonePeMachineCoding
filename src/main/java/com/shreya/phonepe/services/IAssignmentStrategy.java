package com.shreya.phonepe.services;

import com.shreya.phonepe.models.Agent;
import com.shreya.phonepe.models.Issue;

import java.util.List;

public interface IAssignmentStrategy {
    Agent assign(List<Agent> availableAgents, Issue issue);
}
