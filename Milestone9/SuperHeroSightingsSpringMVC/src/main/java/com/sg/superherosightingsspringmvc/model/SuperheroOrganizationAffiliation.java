/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.superherosightingsspringmvc.model;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

public class SuperheroOrganizationAffiliation {
    private int affiliationId;
    
    @NotEmpty(message = "You must supply a valid non-empty value for Superperson ID.")
    private int heroId;
    
    @NotEmpty(message = "You must supply a valid non-empty value for Organization ID.")
    private int orgId;

    public int getAffiliationId() {
        return affiliationId;
    }

    public void setAffiliationId(int affiliationId) {
        this.affiliationId = affiliationId;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.affiliationId;
        hash = 97 * hash + this.heroId;
        hash = 97 * hash + this.orgId;
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
        final SuperheroOrganizationAffiliation other = (SuperheroOrganizationAffiliation) obj;
        if (this.affiliationId != other.affiliationId) {
            return false;
        }
        if (this.heroId != other.heroId) {
            return false;
        }
        if (this.orgId != other.orgId) {
            return false;
        }
        return true;
    }

    
}
