package com.business.application.constants;

public final class Constants {
    public static final String ERROR_VIEW_NAME = "error/500";

    public static final String REDIRECT_URL = "redirect:";
    public static final String ADMIN_URL = "/admin";
    public static final String ID_URL_SUFFIX = "/{id}";

    public static final String PRE_AUTHORIZATION_CONDITION_ANONYMOUS = "isAnonymous()";
    public static final String PRE_AUTHORIZATION_CONDITION_AUTHENTICATED = "isAuthenticated()";
    public static final String PRE_AUTHORIZATION_CONDITION_ADMIN = "hasRole('ROLE_ADMIN')";

    public static final String PASSWORDS_NOT_MATCH_MESSAGE = "Паролите не съвпадат!";
    public static final String EMAIL_ALREADY_EXISTS_MESSAGE = "Акаунт с такъв имейл вече съществува!";
    public static final String USERNAME_ALREADY_EXISTS_MESSAGE = "Акаунт с такова потребителско име вече съществува!";
}
