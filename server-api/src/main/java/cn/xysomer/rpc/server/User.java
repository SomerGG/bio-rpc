package cn.xysomer.rpc.server;

import java.io.Serializable;

/**
 * @Description
 * @Author Somer
 * @Date 2020-03-10 19:46
 */
public class User implements Serializable {

    private String name;

    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
