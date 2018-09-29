package com.pinnet.batch.processor;

import com.pinnet.domain.User;
import org.springframework.batch.item.ItemProcessor;

import java.util.concurrent.atomic.AtomicInteger;

//中间过程的数据处理
public class UserItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        System.out.println(user + "processor");
        return user;
    }
}
