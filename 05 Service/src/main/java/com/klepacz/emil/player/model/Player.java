package com.klepacz.emil.player.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.*;

public class Player {

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @NotEmpty
    @Size(min = 1, max = 16)
    private String name;

    @Size(min = 6, max = 32)
    private String surname;

    @Digits(integer = 3, fraction = 1)
    @NotNull
    @Min(1)
    private Integer age;

    public Player() {
    }

    public Player(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Player(Integer id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (age != player.age) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        return surname != null ? surname.equals(player.surname) : player.surname == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "< " + name + " " + surname + ", age= " + age + " >";
    }
}
