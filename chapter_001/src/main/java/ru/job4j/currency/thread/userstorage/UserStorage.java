package ru.job4j.currency.thread.userstorage;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {

    private List<User> list = new ArrayList<>();

    public synchronized boolean add(User user) {
        synchronized (this) {
            User result = searchId(user.getId());
            if (result == null) {
                list.add(user);
            }
            return (result == null);
        }
    }

    public synchronized boolean update(User user) {
        synchronized (this) {
            User result = searchId(user.getId());
            if (result != null) {
                result.setAmount(user.getAmount());
            }
            return (result != null);
        }
    }

    public synchronized boolean delete(User user) {
        synchronized (this) {
            User result = searchId(user.getId());
            if (result != null) {
                list.remove(result);
            }
            return (result != null);
        }
    }

    private synchronized User searchId(int id) {
        User result = null;
        for (User user : list) {
            if (user.getId() == id) {
                result = user;
                break;
            }
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            User userFrom = searchId(fromId);
            User userTo = searchId(toId);
            boolean isDone = false;
            if (userFrom != null && userTo != null) {
                int operationRes = userFrom.getAmount() - amount;
                if (operationRes >= 0) {
                    userFrom.setAmount(operationRes);
                    userTo.setAmount(operationRes + amount * 2);
                    isDone = true;
                }
            }
            return isDone;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : list) {
            stringBuilder.append("[").append(user.getId()).append("]=").append(user.getAmount()).append(" ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}