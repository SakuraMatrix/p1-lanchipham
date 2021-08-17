package com.github.SakuraMatrix.p1lanchipham.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.SakuraMatrix.p1lanchipham.domain.Category;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CategoryRepository {
    private CqlSession session;

    public CategoryRepository(CqlSession session) {
        this.session = session;
    }

    public Flux<Category> getAll() {
        return Flux.from(session.executeReactive("SELECT * FROM budget.categories"))
                .map(row -> new Category(row.getInt("categoryId"), row.getString("name"), row.getDouble("budgetAmount"), row.getDouble("alertAmount"), row.getDouble("currentUse"), row.getString("status")));
    }

    public Mono<Category> get(int id) {
        return Mono.from(session.executeReactive("SELECT * FROM budget.categories WHERE categoryId = " + id))
                .map(row -> new Category(row.getInt("categoryId"), row.getString("name"), row.getDouble("budgetAmount"), row.getDouble("alertAmount"), row.getDouble("currentUse"), row.getString("status")));
    }

    public Category setInitialValues(Category category) {
        SimpleStatement setInitial = SimpleStatement.builder("INSERT budget.categories (budgetAlert, alertAmount, currentUse) values (?, ?, ?)")
                .addPositionalValues(category.getBudget(), category.getAlert(), category.getCurrentUse())
                .build();
        Flux.from(session.executeReactive(setInitial)).subscribe();
        return category;
    }

    public Category updateCurrentUse(Category category) {
        SimpleStatement updateQuery = SimpleStatement.builder("UPDATE budget.categories SET currentUse = ? WHERE categoryId = ? IF EXISTS")
                .addPositionalValues(category.getCurrentUse(), category.getId())
                .build();
        Flux.from(session.executeReactive(updateQuery)).subscribe();
        return category;
    }

    // public Category update(int id) {
    //     SimpleStatement updateQuery = update(id)
    //             .setColumn("currentUse", bindMarker())
    //             .whereColumn("categoryId").isEqualTo(bindMarker())
    //             .build();
    // }
}
