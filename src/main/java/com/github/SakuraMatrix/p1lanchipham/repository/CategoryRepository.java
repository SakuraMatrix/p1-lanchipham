package com.github.SakuraMatrix.p1lanchipham.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.SakuraMatrix.p1lanchipham.domain.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        SimpleStatement setInitial = SimpleStatement.builder("INSERT INTO budget.categories (budgetAmount, alertAmount, currentUse) values (?, ?, ?)")
                .addPositionalValues(category.getBudget(), category.getAlert(), category.getCurrentUse())
                .build();
        Flux.from(session.executeReactive(setInitial)).subscribe();
        return category;
    }

    public Category updateCurrentUse(Category category) {
        SimpleStatement updateQuery = SimpleStatement.builder("INSERT INTO budget.categories (currentUse) values (?)")
                .addPositionalValues(category.getCurrentUse())
                .build();
        Flux.from(session.executeReactive(updateQuery)).subscribe();
        return category;
    }

//    public Mono<Category> update(int id) {
//        SimpleStatement updateQuery = update(id)
//                .setColumn("spent", bindMarker())
//                .whereColumn("currentUse").isEqualTo(bindMarker())
//                .build();
//        return Mono.from(session.executeReactive(updateQuery);
//    }

    public static class printWelcome {
        public static void printHello(){
            System.out.println("***********************");
            System.out.println("Welcome to the Budget Reminder");
        }
    }
}
