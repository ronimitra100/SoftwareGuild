package com.sg.vendingmachine.advice;
import com.sg.vendingmachine.dao.*;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {
    VendingMachineAuditDao auditDao;
    
    public LoggingAdvice(VendingMachineAuditDao auditDao){
        this.auditDao = auditDao;
    }
    
    public void createAuditEntryUponVendingSuccess(JoinPoint jp, Object returnValue){
        String auditEntry = jp.getSignature().getName()
                +": "
                + "Successfully vended item with "
                + returnValue.toString();
        
        try{
            auditDao.writeAuditEntry(auditEntry);
        }
        catch(Exception e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
    public void createAuditEntryUponVendingFailure(JoinPoint jp, Exception e){
        Object[] args = jp.getArgs();
        
        String auditEntry = jp.getSignature().getName()
                +": "
                + e.getClass().getSimpleName() 
                + " encountered while attempting to vend item with ID " 
                + args[0] 
                + ", after making a deposit of " 
                + args[1] 
                + ".";
        
        try{
            auditDao.writeAuditEntry(auditEntry);
        }
        catch(Exception ex) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
