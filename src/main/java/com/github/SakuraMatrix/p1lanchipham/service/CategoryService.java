package com.github.SakuraMatrix.p1lanchipham.service;

import com.github.SakuraMatrix.p1lanchipham.domain.Category;
import com.github.SakuraMatrix.p1lanchipham.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

    /** 
     * Service layer with methods
     */
    @Service
    public class CategoryService {
        Logger log = LoggerFactory.getLogger(CategoryService.class);
        private final CategoryRepository categoryRepository;

        public CategoryService(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        public Flux<Category> getAll() {
            log.info("retrieving all categories...");
            return categoryRepository.getAll();
        }

        public Mono<Category> get(String id) {
            log.info("retrieving category with categoryId " + id);
            return categoryRepository.get(Integer.parseInt(id));
        }

        public Category create(Category category) {
            log.info("creating new category...");
            return categoryRepository.create(category);
        }

        public Category setInitialValues(Category category) {
            log.info("setting initial values for budget amount, alert amount, and current use...");
            return categoryRepository.setInitialValues(category);
        }

        public Category updateCurrentUse(Category category) {
            log.info("updating current use category...");
            return categoryRepository.updateCurrentUse(category);
        }

    }
