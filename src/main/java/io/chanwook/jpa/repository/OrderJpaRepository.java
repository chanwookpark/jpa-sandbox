package io.chanwook.jpa.repository;

import io.chanwook.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
