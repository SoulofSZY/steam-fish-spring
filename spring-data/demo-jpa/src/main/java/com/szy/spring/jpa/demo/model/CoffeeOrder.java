package com.szy.spring.jpa.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 〈订单〉
 *
 * @author steamedfish
 * @create 2019/9/22
 * @since 1.0.0
 */
@Entity
@Table(name = "T_ORDER")
@Data
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1699683675581107225L;

    private String customer;
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
