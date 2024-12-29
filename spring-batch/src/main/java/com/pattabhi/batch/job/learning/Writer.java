package com.pattabhi.batch.job.learning;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<String> {
    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        System.out.println("Item Writer");
        System.out.println("Writing Data:"+chunk);
    }
}
