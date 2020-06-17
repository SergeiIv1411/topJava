package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, id, authUserId());
    }

    public List<Meal> getAll() {
        log.info("getAll {}");
        return service.getAll(authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}");
        checkNew(meal);
        meal.setUserId(authUserId());
        return service.create(meal);
    }

    public List<Meal> getAll(LocalDate localDateStart, LocalDate localDateEnd, LocalTime localTimeStart, LocalTime localTimeEnd) {
        log.info("getAll with filter{}");
        return service.getAll(authUserId(), localDateStart, localDateEnd, localTimeStart, localTimeEnd);

    }
}