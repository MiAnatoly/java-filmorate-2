package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.friend.FriendsStorage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceTest {
    private final UserService userService;
    private final FriendsStorage friendsStorage;

    @Test
    public void findUserByIdTest() {
        UserDto user = UserDto.builder()
                .name("User1")
                .login("user1")
                .email("1hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user = userService.createUser(user);

        assertNotNull(user);

        assertEquals("User1", userService.findById(user.getId()).getName());
    }

    @Test
    public void updateUserTest() {
        UserDto user = UserDto.builder()
                .name("User1")
                .login("user1")
                .email("1hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user = userService.createUser(user);

        UserDto userToUpdate = UserDto.builder()
                .id(user.getId())
                .name("UpdatedUser")
                .login("user1")
                .email("1hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        userService.updateUser(userToUpdate);

        assertEquals("UpdatedUser", userService.findById(user.getId()).getName());
    }

    @Test
    public void getUsersTest() {
        UserDto user1 = UserDto.builder()
                .name("User1")
                .login("user1")
                .email("1hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        userService.createUser(user1);

        UserDto user2 = UserDto.builder()
                .name("User2")
                .login("user2")
                .email("2hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        userService.createUser(user2);

        assertEquals(2, userService.findAll().size());
    }

    @Test
    public void removeUserTest() {
        UserDto user1 = UserDto.builder()
                .name("User1")
                .login("user1")
                .email("1hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        userService.createUser(user1);

        UserDto user2 = UserDto.builder()
                .name("User2")
                .login("user2")
                .email("2hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user2 = userService.createUser(user2);

        userService.removeUser(user2);

        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void addFriend() {
        UserDto user1 = UserDto.builder()
                .name("User1")
                .login("user1")
                .email("1hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user1 = userService.createUser(user1);

        UserDto user2 = UserDto.builder()
                .name("User2")
                .login("user2")
                .email("2hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user2 = userService.createUser(user2);

        userService.addFriend(user1.getId(), user2.getId());

        assertEquals(1, userService.getAllFriends(user1.getId()).size());
    }

    @Test
    public void deleteFriends() {
        UserDto user1 = UserDto.builder()
                .name("User1")
                .login("user1")
                .email("1hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user1 = userService.createUser(user1);

        UserDto user2 = UserDto.builder()
                .name("User2")
                .login("user2")
                .email("2hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user2 = userService.createUser(user2);

        UserDto user3 = UserDto.builder()
                .name("User3")
                .login("user3")
                .email("3hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user3 = userService.createUser(user3);

        userService.addFriend(user1.getId(), user2.getId());
        userService.addFriend(user1.getId(), user3.getId());
        userService.removeFriend(user1.getId(), user2.getId());

        assertEquals(1, friendsStorage.getAllFriendsUser(user1.getId()).size());
    }

    @Test
    public void getAllFriends() {
        UserDto user1 = UserDto.builder()
                .name("User1")
                .login("user1")
                .email("1hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user1 = userService.createUser(user1);

        UserDto user2 = UserDto.builder()
                .name("User2")
                .login("user2")
                .email("2hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user2 = userService.createUser(user2);

        UserDto user3 = UserDto.builder()
                .name("User3")
                .login("user3")
                .email("3hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user3 = userService.createUser(user3);

        userService.addFriend(user1.getId(), user2.getId());
        userService.addFriend(user1.getId(), user3.getId());
        assertEquals(2, userService.getAllFriends(user1.getId()).size());
    }
}
