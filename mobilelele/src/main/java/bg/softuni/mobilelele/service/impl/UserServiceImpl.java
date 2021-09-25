package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.UserService;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void initializeUsers() {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setActive(true);

            userRepository.save(admin);
        }
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {

        Optional<UserEntity> userEntityOpt =
                userRepository.findByUsername(loginServiceModel.getUsername());

        if (userEntityOpt.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(
                    loginServiceModel.getRawPassword(),
                    userEntityOpt.get().getPassword());
        }
    }
}
