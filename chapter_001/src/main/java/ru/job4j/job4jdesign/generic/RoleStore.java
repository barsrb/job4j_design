package ru.job4j.job4jdesign.generic;

public class RoleStore extends AbstractStore<Role> {
    public RoleStore(int size) {
        super(size);
    }

    @Override
    public Role findById(String id) {
        return super.findById(id);
    }
}
