package ru.kovladimir.map;

import java.util.Objects;

/**
 * Demonstrate equals(Object o) and hashcode().
 */
public class Client {

    /**
     * Name.
     */
    private String name;

    /**
     * Address.
     */
    private String address;

    /**
     * Age.
     */
    private int age;

    /**
     * Constructor.
     * @param name String.
     * @param address String.
     * @param age int.
     */
    public Client(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    /**
     * {@inheritDoc}
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Client otherClient = (Client) o;
        return Objects.equals(name, otherClient.name)
                && Objects.equals(address, otherClient.address)
                && age == otherClient.age;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, address, age);
    }
}
