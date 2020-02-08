package com.updatatech.jasperreportDemo.repository;

import com.updatatech.jasperreportDemo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Category findCategoryById(Long categoryId);
}
