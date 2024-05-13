    package com.example.ecommerce.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;

    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    @Data
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    public class Orders {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "order_id", nullable = false)
        private Long orderId;
        @Column(name = "order_date", nullable = false)
        private Date orderDate;
        @Column(name = "total_amount", nullable = false)
        private Double totalAmount;
        @Column(name = "status", nullable = false)
        private String status;
        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @OneToMany(mappedBy = "orders")
        private List<OrderItems> orderItems = new ArrayList<>();

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
