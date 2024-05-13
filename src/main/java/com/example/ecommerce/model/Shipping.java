package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id", nullable = false)
    private Long shippingId;
    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;
    @Column(name = "shipping_date", nullable = false)
    private Date shippingDate;
    @Column(name = "tracking_number", nullable = false)
    private String trackingNumber;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;


    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "user_id")
    private User updatedBy;
}
