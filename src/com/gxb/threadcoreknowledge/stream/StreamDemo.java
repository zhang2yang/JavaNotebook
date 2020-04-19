package com.gxb.threadcoreknowledge.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *按照给出数据，找出同时满足以下条件的用户，也即以下条件全部满足
 * 偶数Id并且年龄大于24
 * 且用户名转为大写
 * 且用户名字母倒排序
 * 只输出一个用户名字
 */
public class StreamDemo {

    public static void main(String[] args) {
        // stream();
        // streamParallel();
        streamSingleThreadAndMultiThread();
    }

    private static void streamSingleThreadAndMultiThread() {
        System.out.println(String.format("本计算机的核数：%d", Runtime.getRuntime().availableProcessors()));

        // 产生100w个随机数(1 ~ 100)，组成列表
        Random random = new Random();
        List<Integer> list = new ArrayList<>(1000_0000);

        for (int i = 0; i < 10000000; i++) {
            list.add(random.nextInt(100));
        }

        long prevTime = getCurrentTime();
        list.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("单线程计算耗时：%d", getCurrentTime() - prevTime));

        prevTime = getCurrentTime();
        list.stream().parallel().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("多线程计算耗时：%d", getCurrentTime() - prevTime));

    }

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    private static void streamParallel() {
        Stream.of(1,2,3,4,5,6,7,8,9,10).parallel().reduce((a, b) -> {
            System.out.println(String.format("%s:%d + %d = %d", Thread.currentThread().getName(),a,b,a+b));
            return a + b;
        }).ifPresent(sum -> System.out.println("sum:" + sum));
    }

    private static void stream() {
        User user1 = new User(11,"a",23);
        User user2 = new User(12,"b",24);
        User user3 = new User(13,"c",22);
        User user4 = new User(14,"d",28);
        User user5 = new User(16,"e",26);

        List<User> list = Arrays.asList(user1,user2,user3,user4,user5);

        list.stream().filter(user -> user.getId() % 2 == 0).filter(user -> user.getAge() > 24).map(user -> user.getUsername().toUpperCase()).sorted(Comparator.reverseOrder()).limit(1).collect(Collectors.toList()).forEach(System.out::println);

    }
}

class User {

    private Integer id;
    private String username;
    private Integer age;

    public User(Integer id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}


