package com.example.config;

import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.Nullable;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> userContextHolder =
            new NamedThreadLocal<>("user context");

    public static void resetUserContext() {
        userContextHolder.remove();
    }
    public static void setRequestAttributes(@Nullable UserContext userContext) {
        if(userContext == null){
            resetUserContext();
        }else{
            userContextHolder.set(userContext);
        }

    }
    @Nullable
    public static UserContext getUserContext() {
        return userContextHolder.get();
    }

}
