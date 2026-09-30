package com.gdghongik.commerce.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private Order(OrderStatus status) {
        this.status = status;
    }

    public static Order place(OrderItem item) {
        // TODO[W2-7]: CREATED 상태의 Order 를 만들고, addItem 으로 첫 항목을 담아 반환하세요.
        //             항목이 0개인 Order 가 잠깐이라도 바깥에 나가면 안 됩니다.
        Order order = new Order(OrderStatus.CREATED);
        order.addItem(item);
        return order;
    }

    public void addItem(OrderItem item) {
        // TODO[W2-4]: 항목이 null 이면 IllegalArgumentException 을 던지세요.
        //             CREATED 상태가 아니면 IllegalStateException 을 던지세요.
        //             둘 다 통과하면 orderItems 에 담고 item.assignTo(this) 를 부르세요.
        if(item == null) {
            throw new IllegalArgumentException("주문 항목이 있어야 주문할 수 있습니다.");
        }
        if(this.status != OrderStatus.CREATED){
            throw new IllegalStateException("주문 확정 이후에는 항목을 변경할 수 없습니다.");
        }
        this.orderItems.add(item);
        item.assignTo(this);
    }

    public void cancelItem(OrderItem item) {
        if(item == null) {
            throw new IllegalArgumentException("주문 항목이 있어야 주문할 수 있습니다."); //'주문 항목은 1개 이상이어야 한다' 해결(주문 항목이 있다는 것은 주문 항목이 1개 이상이라는 뜻이기도 하기 때문)
        }
        if(this.status != OrderStatus.CREATED) {
            throw new IllegalStateException("주문 확정 이후에는 항목을 변경할 수 없습니다.");
        }
        this.orderItems.remove(item);
        item.assignTo(this);
    }

    public void cancel() {
        // TODO[W2-5]: SHIPPED 또는 DELIVERED 상태면 IllegalStateException 을 던지세요.
        //             정상이면 상태를 CANCELED 로 바꾸세요.
        if(this.status == OrderStatus.SHIPPED || this.status == OrderStatus.DELIVERED){
            throw new IllegalStateException("배송이 시작된 주문은 취소할 수 없습니다.");
        }

        this.status = OrderStatus.CANCELED;
    }

    public Money totalAmount() {
        // TODO[W2-6]: 모든 주문 항목의 subtotal() 을 더한 Money 를 반환하세요.
        return orderItems.stream()
                .map(OrderItem::subtotal)
                .reduce(Money.ZERO, Money::add);
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public void ship() {
        this.status = OrderStatus.SHIPPED;
    }

    public void deliver() {
        this.status = OrderStatus.DELIVERED;
    }
}
