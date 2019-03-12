package kajitool.web.service.account;

import kajitool.web.domain.model.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    public Optional<Account> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(this::authenticationToAccount);
    }
    private Account authenticationToAccount(final Authentication authentication) {
        if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User user = (OAuth2User) authentication.getPrincipal();
            Account account = new Account();
            account.setName((String) user.getAttributes().get("login"));
            return account;
        }
        return null;
    }
}