package micf.taskr.service.user;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import micf.taskr.domain.user.*;
import micf.taskr.exception.user.*;

// Verification
import micf.taskr.domain.verification.*;

public interface UserService {

    User registerNewUserAccount(UserDTO accountDto) throws UserAlreadyExistException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    Optional<User> getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);

    User updateUserLoginToken(boolean loginToken);

    List<String> getUsersFromSessionRegistry();
}