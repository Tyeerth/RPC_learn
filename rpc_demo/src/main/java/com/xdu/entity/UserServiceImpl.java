package com.xdu.entity;

public class UserServiceImpl implements UserService{
    @Override
    public User getUserFriend(User user, String message) {
        System.out.println("execute getUserFriend, user=" + user + ",message=" + message);
        // demo返回一个不同的user对象回去
        return new User(user.getUsername() + ".friend", user.getAge() + 1);
    }
}
