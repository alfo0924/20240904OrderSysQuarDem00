package com.example._20240904ordersysquardem00.service;

import com.example._20240904ordersysquardem00.model.DeliveryStaff;
import com.example._20240904ordersysquardem00.repository.DeliveryStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryStaffService {

    @Autowired
    private DeliveryStaffRepository deliveryStaffRepository;

    public List<DeliveryStaff> getAllDeliveryStaff() {
        return deliveryStaffRepository.findAll();
    }

    public Optional<DeliveryStaff> getDeliveryStaffById(String id) {
        return deliveryStaffRepository.findById(id);
    }

    public DeliveryStaff saveDeliveryStaff(DeliveryStaff deliveryStaff) {
        return deliveryStaffRepository.save(deliveryStaff);
    }

    public void deleteDeliveryStaff(String id) {
        deliveryStaffRepository.deleteById(id);
    }
}