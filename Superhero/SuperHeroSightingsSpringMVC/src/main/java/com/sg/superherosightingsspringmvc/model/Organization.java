package com.sg.superherosightingsspringmvc.model;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Organization {
    private int orgId;
    
    @NotEmpty(message = "You must supply a value for Name.")
    @Length(max = 45, message = "Name must be no more than 45 characters in length.")
    private String orgName;
    
    @NotEmpty(message = "You must supply a value for Description.")
    @Length(max = 45, message = "Description must be no more than 128 characters in length.")
    private String orgDescription;
    
    @NotEmpty(message = "You must supply a value for Address.")
    @Length(max = 45, message = "Address must be no more than 128 characters in length.")
    private String orgAddress;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.orgId;
        hash = 31 * hash + Objects.hashCode(this.orgName);
        hash = 31 * hash + Objects.hashCode(this.orgDescription);
        hash = 31 * hash + Objects.hashCode(this.orgAddress);
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
        final Organization other = (Organization) obj;
        if (this.orgId != other.orgId) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgDescription, other.orgDescription)) {
            return false;
        }
        if (!Objects.equals(this.orgAddress, other.orgAddress)) {
            return false;
        }
        return true;
    }
    
    
}
