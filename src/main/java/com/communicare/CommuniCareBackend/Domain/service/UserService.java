package com.communicare.CommuniCareBackend.Domain.service;

import com.communicare.CommuniCareBackend.Application.dto.response.UserGeneralDto;
import com.communicare.CommuniCareBackend.Domain.entity.User;
import com.communicare.CommuniCareBackend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<UserGeneralDto> getUser(Integer id) {
        UserGeneralDto userGeneralDto = new UserGeneralDto();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userGeneralDto.setId(user.getId());
            userGeneralDto.setName(user.getName());
            return ResponseEntity.ok(userGeneralDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
// example