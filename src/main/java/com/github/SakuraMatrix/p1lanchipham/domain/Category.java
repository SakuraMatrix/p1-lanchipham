package com.github.SakuraMatrix.p1lanchipham.domain;

public class Category {
    private int id;
    private String name;
    private double budget;
    private double alert;
    private double currentUse;
    private String status;

    public Category() {
    }

public Category(int id, String name, double budget, double alert, double currentUse, String status){
    this.id = id;
    this.name = name;
    this.budget= budget;
    this.alert = alert;
    this.currentUse = currentUse;
    this.status = status;
}

    @Override
    public String toString() {
        return "Item{" +
                "id= " + id +
                ", name='" + name + '\'' +
                ", budget= " + budget +
                ", alert= " + alert +
                ", currentUse= " + currentUse +
                ", status=' " + status + '\'' +
                '}';
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

    public double getCurrentUse() {
        return currentUse;
    }

    public void setCurrentUse(double currentUse) {
        this.currentUse = currentUse;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String status) {
        this.status = status;
    }
}


