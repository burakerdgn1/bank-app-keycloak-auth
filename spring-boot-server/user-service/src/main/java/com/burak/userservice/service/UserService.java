package com.burak.userservice.service;

import com.burak.userservice.entity.User;
import com.burak.userservice.repository.UserRepository;
import com.burak.userservice.security.UserInfoDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userServiceImpl")
@Primary
public class UserService implements UserDetailsService {

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final PasswordEncoder passwordEncoder;

  public User registerUser(String username, String password) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("Username already exists");
    }
    User user = new User(null, username, passwordEncoder.encode(password));
    return userRepository.save(user);
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
      .map(UserInfoDetails::new)
      .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }
}
