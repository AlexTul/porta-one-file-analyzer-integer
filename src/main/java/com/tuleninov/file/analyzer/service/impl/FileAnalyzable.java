package com.tuleninov.file.analyzer.service.impl;

import com.tuleninov.file.analyzer.model.FileData;
import com.tuleninov.file.analyzer.service.Analyzable;

import java.util.Arrays;

public class FileAnalyzable implements Analyzable {

    @Override
    public FileData analyze(int[] numbers) {
        if (numbers.length == 0) {
            throw new RuntimeException("Array`s length == 0, because file is empty.");
        }

        int maxNumber = max(numbers);
        int minNumber = min(numbers);
        double medianNumber = median(numbers);
        double averageNumber = averageNumber(numbers);
        int[] increasingSequence = increasingSequence(numbers);
        int[] decreasingSequence = decreasingSequence(numbers);

        return new FileData(
                maxNumber,
                minNumber,
                medianNumber,
                averageNumber,
                increasingSequence,
                decreasingSequence);
    }

    private int max(int[] numbers) {
        int maxNumber = Integer.MIN_VALUE;
        for (int number : numbers) {
            maxNumber = maxNumber < number ? number : maxNumber;
        }
        return maxNumber;
    }

    private int min(int[] numbers) {
        int minNumber = Integer.MAX_VALUE;
        for (int number : numbers) {
            minNumber = minNumber > number ? number : minNumber;
        }
        return minNumber;
    }

    private double median(int[] numbers) {
        int size = numbers.length;
        int[] dest = new int[size];
        System.arraycopy(numbers, 0, dest, 0, numbers.length);
        Arrays.sort(dest);

        if (size % 2 == 0) {
            double middle1 = dest[size / 2 - 1];
            double middle2 = dest[size / 2];
            return Math.round((middle1 + middle2) / 2.0 * 100.0) / 100.0;
        } else {
            return dest[size / 2];
        }
    }

    private double averageNumber(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return Math.round((double) sum / numbers.length * 100.0) / 100.0;
    }

    private int[] increasingSequence(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return new int[0];
        }

        int[] currentSequence = new int[numbers.length];
        int currentSize = 0;

        int[] largestSequence = new int[0];
        int largestSize = 0;

        for (int number : numbers) {
            if (currentSize == 0 || number > currentSequence[currentSize - 1]) {
                currentSequence[currentSize] = number;
                currentSize++;
            } else {
                if (currentSize > largestSize) {
                    largestSequence = new int[currentSize];
                    System.arraycopy(currentSequence, 0, largestSequence, 0, currentSize);
                    largestSize = currentSize;
                }
                currentSequence[0] = number;
                currentSize = 1;
            }
        }

        largestSequence = updateLargestSequence(currentSequence, currentSize, largestSequence, largestSize);

        return largestSequence;
    }

    private int[] decreasingSequence(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return new int[0];
        }

        int[] currentSequence = new int[numbers.length];
        int currentSize = 0;

        int[] largestSequence = new int[0];
        int largestSize = 0;

        for (int number : numbers) {
            if (currentSize == 0 || number < currentSequence[currentSize - 1]) {
                currentSequence[currentSize] = number;
                currentSize++;
            } else {
                if (currentSize > largestSize) {
                    largestSequence = new int[currentSize];
                    System.arraycopy(currentSequence, 0, largestSequence, 0, currentSize);
                    largestSize = currentSize;
                }
                currentSequence[0] = number;
                currentSize = 1;
            }
        }

        largestSequence = updateLargestSequence(currentSequence, currentSize, largestSequence, largestSize);

        return largestSequence;
    }

    private int[] updateLargestSequence(int[] currentSequence, int currentSize, int[] largestSequence, int largestSize) {
        if (currentSize > largestSize) {
            largestSequence = Arrays.copyOf(currentSequence, currentSize);
        }
        return largestSequence;
    }
}
