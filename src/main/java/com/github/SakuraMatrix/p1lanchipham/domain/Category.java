package com.github.SakuraMatrix.p1lanchipham.domain;

import java.util.Objects;

public class Category {
    private int id;
    private String name;
    private double budget;
    private double alert;
    private double current;
    private String status;

    public Category() {
    }

public Category(int id, String name, double budget, double alert, double current, String status){
    this.id = id;
    this.name = name;
    this.budget= budget;
    this.alert = alert;
    this.current = current;
    this.status = status;
}

    @Override
    public String toString() {
        return "Item{" +
                "id= " + id +
                ", name='" + name + '\'' +
                ", budget= " + budget +
                ", alert= " + alert +
                ", current= " + current +
                ", status=' " + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())return false;
        Category category = (Category) obj;
        return id == category.id 
        && Objects.equals(name, category.name) 
        && Double.compare(category.budget, budget) == 0
        && Double.compare(category.alert, alert ) == 0 
        && Double.compare(category.current, current) == 0
        && Objects.equals(status, category.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, budget, alert, current, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getAlert() {
        return alert;
    }

    public void setAlert(double alert) {
        this.alert = alert;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String status) {
        this.status = status;
    }
}


