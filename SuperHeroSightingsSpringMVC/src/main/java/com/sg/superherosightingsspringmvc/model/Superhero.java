package com.sg.superherosightingsspringmvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Superhero {
    private int heroId;
    
    @NotEmpty(message = "You must supply a value for Name.")
    @Length(max = 45, message = "Name must be no more than 45 characters in length.")
    private String heroName;
    
    @NotEmpty(message = "You must supply a value for Description.")
    @Length(max = 45, message = "Description must be no more than 128 characters in length.")
    private String heroDescription;
    
    @NotEmpty(message = "You must supply a value for Superpower.")
    @Length(max = 45, message = "Superpower must be no more than 128 characters in length.")
    private String heroSuperPower;

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroDescription() {
        return heroDescription;
    }

    public void setHeroDescription(String heroDescription) {
        this.heroDescription = heroDescription;
    }

    public String getHeroSuperPower() {
        return heroSuperPower;
    }

    public void setHeroSuperPower(String heroSuperPower) {
        this.heroSuperPower = heroSuperPower;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.heroId;
        hash = 41 * hash + Objects.hashCode(this.heroName);
        hash = 41 * hash + Objects.hashCode(this.heroDescription);
        hash = 41 * hash + Objects.hashCode(this.heroSuperPower);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Superhero other = (Superhero) obj;
        if (this.heroId != other.heroId) {
            return false;
        }
        if (!Objects.equals(this.heroName, other.heroName)) {
            return false;
        }
        if (!Objects.equals(this.heroDescription, other.heroDescription)) {
            return false;
        }
        if (!Objects.equals(this.heroSuperPower, other.heroSuperPower)) {
            return false;
        }
        return true;
    }

}
