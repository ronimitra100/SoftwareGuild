package com.sg.javadatetimeapi;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
//import java.time.ZonedDateTime;
import java.time.*;

public class App 
{
    public static void main( String[] args )
    {   LocalDate ld = LocalDate.now();
        System.out.println( "Local Date now: " + ld );
        
        ld = LocalDate.parse("2015-03-01") ;
        System.out.println(ld);
        
        ld = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy")) ;
        System.out.println(ld);
        
        ld = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("dd/MM/yyyy")) ;
        System.out.println(ld);
        
        String isoDate = ld.toString();
        System.out.println(isoDate);
        
        ld = LocalDate.parse(isoDate);
        System.out.println(ld);
        
        String formatted = ld.format(DateTimeFormatter.ofPattern("dd/MM/yyyy +=+-"));
        System.out.println(formatted);
        
        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        System.out.println(formatted);
        
        LocalDate past = ld.minusDays(6);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        past = ld.minusMonths(3);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        Period diff = ld.until(past);
        System.out.println(diff);
        System.out.println(diff.getMonths());
        
        diff = past.until(ld);
        System.out.println(diff.getMonths());
        
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        formatted = ldt.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
        System.out.println(formatted);
        
        Date legacyDate = new Date();
        System.out.println(legacyDate);
        
        GregorianCalendar legacyCalendar = new GregorianCalendar();
        System.out.println(legacyCalendar);
        
        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        ld = zdt.toLocalDate();
        System.out.println(ld);
        
        ld = legacyCalendar.toZonedDateTime().toLocalDate();
        System.out.println(ld);
    }
}
