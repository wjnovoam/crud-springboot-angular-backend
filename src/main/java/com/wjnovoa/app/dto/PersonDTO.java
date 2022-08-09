package com.wjnovoa.app.dto;

/**
 * @author William Johan Novoa Melendrez
 * @date 4/08/2022
 */
public class PersonDTO {

    private Long id;
    private String name;
    private String lastname;
    private String document;
    private int age;
    private double alture;
    private String size;
    private double weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAlture() {
        return alture;
    }

    public void setAlture(double alture) {
        this.alture = alture;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}