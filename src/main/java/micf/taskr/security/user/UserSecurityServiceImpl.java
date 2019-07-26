package micf.taskr.security.user;

public interface UserSecurityServiceImpl {

    String validatePasswordResetToken(long id, String token);

}