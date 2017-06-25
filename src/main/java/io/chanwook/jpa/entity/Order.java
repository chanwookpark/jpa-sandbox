package io.chanwook.jpa.entity;

import io.chanwook.jpa.entity.listener.CreateAuditListener;
import io.chanwook.jpa.entity.listener.UpdateAuditListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author chanwook
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "ORD_M")
@EntityListeners(CreateAuditListener.class)
public class Order extends UpdateAuditListener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORD_NO")
    private long orderNumber;

    @Column(name = "ORD_TOT_AMT")
    private long orderTotalAmount;

    public Order(long orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }
}
