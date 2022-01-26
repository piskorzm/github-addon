package com.mpiskorz.github.addon.service;

import com.mpiskorz.github.addon.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String LOGIN = "piskorzm";

    @Mock
    private GithubService githubService;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private UserService userService;



    @Test
    public void shouldPassUserRetrievedByGithubServiceAndCallIncrementLoginRequestCount() {
        // given
        User expectedUser = new User(29603920,
                LOGIN,
                "Marcin Piskorz",
                "User",
                "https://avatars.githubusercontent.com/u/29603920?v=4",
                Instant.parse("2017-06-21T13:44:49Z"),
                27.0);

        when(githubService.getUser(LOGIN)).thenReturn(expectedUser);

        // when
        User user = userService.getUser(LOGIN);

        // then
        assertThat(user).usingRecursiveComparison().isEqualTo(expectedUser);
        verify(loginService).incrementLoginRequestCount(LOGIN);
    }

    @Test
    public void shouldNotCallIncrementLoginRequestCountWhenUserIsNull() {
        // given
        when(githubService.getUser(LOGIN)).thenReturn(null);

        // when
        User user = userService.getUser(LOGIN);

        // then
        assertThat(user).usingRecursiveComparison().isEqualTo(null);
        verify(loginService, never()).incrementLoginRequestCount(LOGIN);
    }
}
