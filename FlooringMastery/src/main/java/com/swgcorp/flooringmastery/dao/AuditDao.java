package com.swgcorp.flooringmastery.dao;

public interface AuditDao {

    public void writeAuditEntry(String entry) throws PersistenceException;
}
