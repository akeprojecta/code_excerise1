package com.exercise.services;

import com.exercise.model.User;
import com.exercise.repository.UserRepository;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService
{
  private UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository)
  {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    User user = userRepository.findByUsername(username);
    if(user == null)
    {
      throw new UsernameNotFoundException(username);
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
  }
}
