package com.futureB.backend.Service;

import com.futureB.backend.DTO.userDTO;
import com.futureB.backend.config.JwtService;
import com.futureB.backend.Entity.User;
import com.futureB.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User activateUser(User user) {
        Optional<User> userInDB = userRepository.findByEmailId(user.getEmailId());
        if(userInDB.isPresent()){
            User existingUser = userInDB.get();
            existingUser.setEnabled(true);
            return userRepository.save(existingUser);
        }else{
            return null;
        }
        //TODO delete the activation token entry after successful activation

    }

    public List<userDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::userToDTO)
                .collect(Collectors.toList());
    }

    public userDTO userToDTO(User user){
        userDTO userDTO = new userDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setDOB(user.getDOB());
        return userDTO;
    }
    

    
}
