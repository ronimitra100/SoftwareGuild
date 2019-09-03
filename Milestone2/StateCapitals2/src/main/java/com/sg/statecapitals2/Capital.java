package com.sg.statecapitals2;

public class Capital {
    String name;
    Integer population;
    Integer squareMileage;
    
    public Capital(String name, Integer population, Integer squareMileage){
        this.name = name;
        this.population = population;
        this.squareMileage = squareMileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getSquareMileage() {
        return squareMileage;
    }

    public void setSquareMileage(Integer squareMileage) {
        this.squareMileage = squareMileage;
    }
}
