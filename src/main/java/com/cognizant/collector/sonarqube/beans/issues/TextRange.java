package com.cognizant.collector.sonarqube.beans.issues;

import lombok.Data;

@Data
public class TextRange {
    private int startLine;
    private int endLine;
    private int startOffset;
    private int endOffset;
}
