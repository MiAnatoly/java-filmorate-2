package ru.yandex.practicum.filmorate.storage.like;

public interface LikeStorage {
    void putLike(int id, int userId);

    void removeLikes(int id, int userId);

    void removeLikesFilm(int id);

    void removeLikesUser(int id);
}
