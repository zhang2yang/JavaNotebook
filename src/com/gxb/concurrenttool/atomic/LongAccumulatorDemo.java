package com.gxb.concurrenttool.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * 描述：     演示LongAccumulator的用法
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) {
        // LongAccumulator accumulator = new LongAccumulator((x, y) -> x +y, 0);
        // LongAccumulator accumulator = new LongAccumulator(Math::max, 0);
        LongAccumulator accumulator = new LongAccumulator((x,y) -> x * y, 0);
        // accumulator.accumulate(1);
        // accumulator.accumulate(2);
        ExecutorService executor = Executors.newFixedThreadPool(8);
        IntStream.range(1,10).forEach(value -> executor.submit(() -> accumulator.accumulate(value)));
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println(accumulator.getThenReset());
    }
}
