package kajitool.web.controller;

import io.swagger.annotations.ApiOperation;
import kajitool.web.domain.model.Account;
import kajitool.web.service.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/acount")
public class AccountResource {
    private final AccountService service;
    public AccountResource(AccountService service) {
        this.service = service;
    }
    @GetMapping("/")
    @ApiOperation(value="ログインアカウントを返します。", nickname="account_get")
    public ResponseEntity<Account> get() {
        return ResponseEntity.ok()
                .body(service.getCurrentUserLogin().orElseThrow(
                        () -> new RuntimeException("Account could not be found")));
    }
}
