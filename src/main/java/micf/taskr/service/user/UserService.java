package micf.taskr.service.user;

import java.util.Optional;

import micf.taskr.domain.user.User;

public interface UserService {

    User saveUser(User user);

    // User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    // void deleteUser(User user);

    // void createVerificationTokenForUser(User user, String token);

    // VerificationToken getVerificationToken(String VerificationToken);

    // VerificationToken generateNewVerificationToken(String token);

    // void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    // PasswordResetToken getPasswordResetToken(String token);

    // User getUserByPasswordResetToken(String token);

    Optional<User> getUserByID(long id);

    void changeUserPassword(User user, String password);

    // boolean checkIfValidOldPassword(User user, String password);

    // String validateVerificationToken(String token);

    // User updateUserLoginToken(boolean loginToken);

    // List<String> getUsersFromSessionRegistry();

}