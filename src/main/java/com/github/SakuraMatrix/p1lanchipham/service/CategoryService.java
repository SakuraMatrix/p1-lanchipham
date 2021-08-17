package com.github.SakuraMatrix.p1lanchipham.service;

import com.github.SakuraMatrix.p1lanchipham.domain.Category;
import com.github.SakuraMatrix.p1lanchipham.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

    @Service
    public class CategoryService {
        private final CategoryRepository categoryRepository;

        public CategoryService(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        public Flux<Category> getAll() {

            return categoryRepository.getAll();
        }

        public Mono<Category> get(String id) {
            return categoryRepository.get(Integer.parseInt(id));
        }

        public Category setInitialValues(Category category) {
            return categoryRepository.setInitialValues(category);
        }

        public Category updateCurrentUse(Category category) {

            return categoryRepository.updateCurrentUse(category);
        }

    }
