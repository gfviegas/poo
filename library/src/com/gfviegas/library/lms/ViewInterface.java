package com.gfviegas.library.lms;

import com.gfviegas.library.user.AuthService;
import com.gfviegas.library.user.User;

public interface ViewInterface {
    User authenticatedUser = null;
    AuthService AUTH_SERVICE = null;

    public User getAuthenticatedUser();


    public void setAuthenticatedUser(User authenticatedUser);

}
