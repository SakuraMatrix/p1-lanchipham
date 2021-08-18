package com.github.SakuraMatrix.p1lanchipham.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.SakuraMatrix.p1lanchipham.domain.Category;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class CategoryRepository {
    Logger log = LoggerFactory.getLogger(CategoryRepository.class);

    private CqlSession session;

    public CategoryRepository(CqlSession session) {
        this.session = session;
    }

    public Flux<Category> getAll() {
        log.info("retrieving all categories from database...");
        return Flux.from(session.executeReactive("SELECT * FROM budget.categories"))
                .map(row -> new Category(row.getInt("id"), row.getString("name"), row.getDouble("budget_amount"), row.getDouble("alert_amount"), row.getDouble("current_use"), row.getString("status")));
    }

    public Mono<Category> get(int id) {
        log.info("retrieving category from database by id...");
        return Mono.from(session.executeReactive("SELECT * FROM budget.categories WHERE id = " + id))
                .map(row -> new Category(row.getInt("id"), row.getString("name"), row.getDouble("budget_amount"), row.getDouble("alert_amount"), row.getDouble("current_use"), row.getString("status")));
    }

    public Category setInitialValues(Category category) {
        log.info("setting initial values for budget, alert, and current use...");
        SimpleStatement setInitial = SimpleStatement.builder("INSERT budget.categories (budget_alert, alert_amount, current_use) values (?, ?, ?)")
                .addPositionalValues(category.getBudget(), category.getAlert(), category.getCurrentUse())
                .build();
        Flux.from(session.executeReactive(setInitial)).subscribe();
        return category;
    }

    public Category updateCurrentUse(Category category) {
        log.info("updating current use category...");
        SimpleStatement updateQuery = SimpleStatement.builder("UPDATE budget.categories SET current_use = ? WHERE id = ? IF EXISTS")
                .addPositionalValues(category.getCurrent(), category.getId())
                .build();
        Flux.from(session.executeReactive(updateQuery)).subscribe();
        return category;
    }

    // public Category update(int id) {
    //     SimpleStatement updateQuery = update(id)
    //             .setColumn("current_use", bindMarker())
    //             .whereColumn("id").isEqualTo(bindMarker())
    //             .build();
    // }
}
