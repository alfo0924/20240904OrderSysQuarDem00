package com.example._20240904ordersysquardem00.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "delivery_staff")
@Getter
@Setter
public class DeliveryStaff {
    @Id
    private String deliveryId;
    private String deliveryName;
    private String deliveryPhone;
    private String deliveryVehicle;
    private String deliveryStatus;
}