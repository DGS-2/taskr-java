package micf.taskr.security.login;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 10;

    private int attemptsCache;

    public LoginAttemptService() {
        super();
        
    }

    //

    public void loginSucceeded(final String key) {
        
    }

    public void loginFailed(final String key) {
        int attempts = 0;
        
        attempts++;
        this.attemptsCache = attempts;
    }

    // public boolean isBlocked(final String key) {
    //     try {
    //         return attemptsCache.get(key) >= MAX_ATTEMPT;
    //     } catch (final ExecutionException e) {
    //         return false;
    //     }
    // }
}