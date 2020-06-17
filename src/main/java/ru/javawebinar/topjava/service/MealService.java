package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<Meal> getAll(int userId) {
        return (List<Meal>) repository.getAll(userId);
    }

    public void update(Meal meal, int id, int userId) {
        if (repository.get(id, userId) == null) {
            throw new NotFoundException("Not found meal in database");
        }
    }

    public List<Meal> getAll(int authUserId, LocalDate localDateStart, LocalDate localDateEnd, LocalTime localTimeStart, LocalTime localTimeEnd) {
        return (List<Meal>) repository.getAll(authUserId, localDateStart, localDateEnd, localTimeStart, localTimeEnd);
    }
}