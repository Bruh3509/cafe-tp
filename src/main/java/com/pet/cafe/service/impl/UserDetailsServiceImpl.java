//package com.pet.cafe.service.impl;
//
//import com.pet.cafe.entity.User;
//import com.pet.cafe.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class UserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String passportId) throws UsernameNotFoundException {
//        User user = userRepository.findById(Long.valueOf(passportId))
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with passportId: " + passportId));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getPassportId(), user.getPassword(), Collections.emptyList());
//    }
//}
