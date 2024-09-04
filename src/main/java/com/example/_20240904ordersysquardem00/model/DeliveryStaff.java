package com.example._20240904ordersysquardem00.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "delivery_staff")
public class DeliveryStaff {
    @Id
    private String deliveryId;

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void setDeliveryVehicle(String deliveryVehicle) {
        this.deliveryVehicle = deliveryVehicle;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    private String deliveryName;
    private String deliveryPhone;
    private String deliveryVehicle;
    private String deliveryStatus;

    // Getters and setters
    // ... (省略getter和setter方法，與之前的類似)
}