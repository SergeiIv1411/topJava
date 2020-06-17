package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UserUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private Map<String, User> repository = new ConcurrentHashMap<>();

    {
        UserUtils.USERS.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        for (Map.Entry<String, User> entry : repository.entrySet()) {
            if (entry.getValue().getId() == id) {
                repository.remove(entry.getKey());
                return true;
            }
        }
        return false;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        repository.put(user.getEmail(), user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        for (Map.Entry<String, User> entry : repository.entrySet()) {
            if (entry.getValue().getId() == id) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> users = (List<User>) repository.values();
        users.sort(Comparator.comparing(User :: getName));
        return users;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.get(email);
    }
}
