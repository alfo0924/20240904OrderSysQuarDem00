package com.example._20240904ordersysquardem00.repository;

import com.example._20240904ordersysquardem00.model.DeliveryStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryStaffRepository extends JpaRepository<DeliveryStaff, String> {
}