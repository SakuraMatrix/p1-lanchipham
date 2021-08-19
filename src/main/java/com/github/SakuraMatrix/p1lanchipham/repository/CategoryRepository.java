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

    //method to get all categories
    public Flux<Category> getAll() {
        log.info("retrieving all categories from database...");
        return Flux.from(session.executeReactive("SELECT * FROM budget.categories"))
                .map(row -> new Category(
                    row.getInt("categoryId"), 
                    row.getString("name"), 
                    row.getDouble("budgetAmount"), 
                    row.getDouble("alertAmount"), 
                    row.getDouble("currentUse"), 
                    row.getString("status")));
    }

    //method to get one category
    public Mono<Category> get(int id) {
        log.info("retrieving category from database by categoryId...");
        return Mono.from(session.executeReactive("SELECT * FROM budget.categories WHERE categoryId = " + id))
                .map(row -> new Category(row.getInt("categoryId"), row.getString("name"), row.getDouble("budgetAmount"), row.getDouble("alertAmount"), row.getDouble("currentUse"), row.getString("status")));
    }

    // method to set values for budget amount, alert amount, and current use
    public Category setInitialValues(Category category) {
        log.info("setting initial values for budget, alert, and current use...");
        SimpleStatement setInitial = SimpleStatement.builder("INSERT budget.categories (budgetAmount, alertAmount, currentUse) values (?, ?, ?)")
                .addPositionalValues(category.getBudget(), category.getAlert(), category.getCurrent())
                .build();
        Flux.from(session.executeReactive(setInitial)).subscribe();
        return category;
    }

    // method to update current use category
    public Category updateCurrentUse(Category category) {
        log.info("updating current use category...");
        SimpleStatement updateQuery = SimpleStatement.builder("UPDATE budget.categories SET currentUse = ? WHERE categoryId = ? IF EXISTS")
                .addPositionalValues(category.getCurrent(), category.getId())
                .build();
        Flux.from(session.executeReactive(updateQuery)).subscribe();
        return category;
    }

    // public Category update(int categoryId) {
    //     SimpleStatement updateQuery = update(categoryId)
    //             .setColumn("currentUse", bindMarker())
    //             .whereColumn("categoryId").isEqualTo(bindMarker())
    //             .build();
    // }
}
