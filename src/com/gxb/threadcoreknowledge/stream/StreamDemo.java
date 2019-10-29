package com.gxb.threadcoreknowledge.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *按照给出数据，找出同时满足以下条件的用户，也即以下条件全部满足
 * 偶数Id并且年龄大于24
 * 且用户名转为大写
 * 且用户名字母倒排序
 * 只输出一个用户名字
 */
public class StreamDemo {

    public static void main(String[] args) {
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


