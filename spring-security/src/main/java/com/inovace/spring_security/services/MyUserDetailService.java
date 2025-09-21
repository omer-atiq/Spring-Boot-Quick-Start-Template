package com.inovace.spring_security.services;

import com.inovace.spring_security.models.MyUserDetail;
import com.inovace.spring_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Optional<com.inovace.spring_security.entities.User> user =  userRepository.findByUsername(username);

      user.orElseThrow(()-> new UsernameNotFoundException("Not founs: " + username));


      return  user.map(MyUserDetail::new).get();

//        MyUserDetail myUserDetail = new MyUserDetail(username, "pass");

//        return new User(myUserDetail.getUsername(), myUserDetail.getPassword(), new ArrayList<>());

    }



}
