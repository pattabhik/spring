package com.pattabhi.batch.job.learning;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String,String> {
    @Override
    public String process(String item) throws Exception {
        System.out.println("Inside Processing "+item+"");
        return "PROCESSED "+item+"";
    }
}
