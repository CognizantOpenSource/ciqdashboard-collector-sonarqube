package com.cognizant.collector.sonarqube.beans.issues;

public class Types {
    public enum Status {
        OPEN, CONFIRMED, REOPENED, RESOLVED, CLOSED;
    }

    public enum Severity {
        INFO, MINOR, MAJOR, CRITICAL, BLOCKER;
    }

    public enum IssueType {
        CODE_SMELL, BUG, VULNERABILITY, SECURITY_HOTSPOT;
    }
}
