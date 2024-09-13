package com.shreya.phonepe.services;

import com.shreya.phonepe.services.impl.InMemoryAgentService;
import com.shreya.phonepe.services.impl.InMemoryIssueService;
import com.shreya.phonepe.services.impl.SimpleAssignmentStrategy;

public class ServiceFactory {
    public static IIssueService createIssueService() {
        return new InMemoryIssueService();
    }

    public static IAgentService createAgentService() {
        return new InMemoryAgentService();
    }

    public static IAssignmentStrategy createAssignmentStrategy() {
        return new SimpleAssignmentStrategy();
    }
}
