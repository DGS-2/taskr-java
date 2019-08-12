package micf.taskr.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import micf.taskr.domain.user.User;
import micf.taskr.exception.register.UsernameAlreadyExistsException;
import micf.taskr.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }

    @Override
    public void saveRegisteredUser(User user) {

    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public Optional<User> getUserByID(long id) {
        return null;
    }

    @Override
    public void changeUserPassword(User user, String password) {

    }

}