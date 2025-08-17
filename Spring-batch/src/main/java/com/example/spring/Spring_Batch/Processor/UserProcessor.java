package com.example.spring.Spring_Batch.Processor;

import com.example.spring.Spring_Batch.Models.User;
import org.springframework.batch.item.ItemProcessor;

public class UserProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(final User user) {
        System.out.println("Processing user: " + user);
         String upperCaseName = user.getName().toUpperCase();
        if (upperCaseName.toLowerCase().contains("john")){
            upperCaseName  = "Omer Atiq";
        }
        final User transformedUser = new User();
        transformedUser.setId(user.getId());
        transformedUser.setName(upperCaseName);
        return transformedUser;
    }
}
