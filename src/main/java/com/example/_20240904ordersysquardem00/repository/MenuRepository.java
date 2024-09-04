package com.example._20240904ordersysquardem00.repository;

import com.example._20240904ordersysquardem00.model.Menu; // 确保导入的是正确的 Menu 类
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
}