package io.github.materialcontrol.ms_users.common;

import io.github.materialcontrol.ms_users.entities.enums.Role;
import io.github.materialcontrol.ms_users.entities.user.User;
import io.github.materialcontrol.ms_users.entities.user.dtos.UserUpdateDto;
import io.github.materialcontrol.ms_users.entities.user.dtos.UserUpdatePasswordDto;

public class UserConstants {
    public static final String ENCODED_PASSWORD = "123456-EncodedPassword";
    public static final User VALID_USER = new User("Jonatha", "jowgaze", "123456");
    public static final User VALID_USER_SAVED = new User(1L,"Jonatha", "jowgaze", ENCODED_PASSWORD, Role.ROLE_PUBLIC);

    public static final UserUpdateDto USER_UPDATE_DTO = new UserUpdateDto("Jonatha Carvalho", "jonatha1");
    public static final User VALID_USER_UPDATE = new User(1L,"Jonatha Carvalho", "jonatha1", ENCODED_PASSWORD, Role.ROLE_PUBLIC);

    public static final UserUpdatePasswordDto USER_UPDATE_PASSWORD_DTO = new UserUpdatePasswordDto("123456", "jow123", "jow123");
    public static final String ENCODED_PASSWORD_UPDATE = "jow123-EncodedPassword";


}
