package ru.job4j.job4jdesign.generic;

public class UserStore extends AbstractStore<User> {
    public UserStore(int size) {
        super(size);
    }

    @Override
    public User findById(String id) {
        return super.findById(id);
    }
}
