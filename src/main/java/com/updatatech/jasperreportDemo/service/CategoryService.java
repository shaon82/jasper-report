package com.updatatech.jasperreportDemo.service;


import com.updatatech.jasperreportDemo.model.Category;
import com.updatatech.jasperreportDemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

//    public Category findCategoryById(Long categoryId) {
//        return categoryRepository.findCategoryById(categoryId);
//    }
}
