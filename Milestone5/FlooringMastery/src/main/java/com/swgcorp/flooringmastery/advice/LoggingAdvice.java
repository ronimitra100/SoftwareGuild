package com.swgcorp.flooringmastery.advice;

import com.swgcorp.flooringmastery.dao.*;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {

    AuditDao auditDao;

    public LoggingAdvice(AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntryUponSuccessfulOrderCreation(JoinPoint jp, Object returnValue) {
        String auditEntry = jp.getSignature().getName()
                + ": "
                + "Successfully created order with "
                + returnValue.toString();

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (Exception e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createAuditEntryUponSuccessfulOrderUpdate(JoinPoint jp, Object returnValue) {
        Object[] args = jp.getArgs();

        String auditEntry = jp.getSignature().getName()
                + ": "
                + "Successfully updated order with"
                + " Order Number : "
                + args[0]
                + " and Order Date: "
                + args[3]
                + "."
                + "The updated order has"
                + " Customer Name: "
                + args[1]
                + ", State: "
                + args[2]
                + ", Product Type: "
                + args[4]
                + ", Area: "
                + args[5]
                + ".";

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (Exception e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createAuditEntryUponSuccessfulOrderRemoval(JoinPoint jp, Object returnValue) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName()
                + ": "
                + "Successfully updated order with"
                + " Order Number : "
                + args[0]
                + " and Order Date: "
                + args[1]
                + ".";

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (Exception e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createAuditEntryUponSuccessfulDisplayOfListOfOrders(JoinPoint jp, Object returnValue) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName()
                + ": "
                + "Successfully displayed orders from ";

        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        auditEntry += ".";

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (Exception e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createAuditEntryUponFailureOfFlooringOperation(JoinPoint jp, Exception e) {
        Object[] args = jp.getArgs();

        String auditEntry = jp.getSignature().getName()
                + ": "
                + e.getClass().getSimpleName()
                + " encountered while attempting to perform flooring operation.";

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (Exception ex) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

}
