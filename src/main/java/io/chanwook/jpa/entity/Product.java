package io.chanwook.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chanwook
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    private String productId;

    @Column(length = 200, nullable = false)
    private String displayName;

    @Column(nullable = false)
    private LocalDateTime enrolled;

    @Column(nullable = false)
    private LocalDateTime updated;

    public Product() {
    }
}
