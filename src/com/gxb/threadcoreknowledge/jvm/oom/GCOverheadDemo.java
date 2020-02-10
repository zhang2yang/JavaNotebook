package com.gxb.threadcoreknowledge.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * ********************i=141019
 * [Full GC (Ergonomics) [PSYoungGen: 2047K->2047K(2560K)] [ParOldGen: 7051K->7047K(7168K)] 9099K->9095K(9728K), [Metaspace: 3663K->3663K(1056768K)], 0.0433231 secs] [Times: user=0.47 sys=0.00, real=0.04 secs]
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class GCOverheadDemo {

    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(i++).intern());
            }
        } catch (Throwable e) {
            System.out.println("********************i=" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
