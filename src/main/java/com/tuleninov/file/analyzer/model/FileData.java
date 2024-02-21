package com.tuleninov.file.analyzer.model;

public record FileData(int maxNumber,
                       int minNumber,
                       double medianNumber,
                       double averageNumber,
                       int[] increasingSequence,
                       int[] decreasingSequence) {
}
