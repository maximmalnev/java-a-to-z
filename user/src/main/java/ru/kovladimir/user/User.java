package ru.kovladimir.user;

/**
 * User with id, name and age.
 */
public class User {

    /**
     * ID.
     */
    private long id;

    /**
     * Name.
     */
    private String name;

    /**
     * Age.
     */
    private int age;

    /**
     * Constructor.
     * @param id long.
     * @param name String.
     * @param age int.
     */
    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
