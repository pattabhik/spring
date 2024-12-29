package com.pattabhi.batch.job.learning;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {
    private String[] coursess = {"Java","Spring","Hibernate","Angular"};
    private int count = 0;
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Reading Data:"+count);
        if(count < coursess.length){
            return coursess[count++];
        }else {
            count=0;
        }
        return null;
    }
}
