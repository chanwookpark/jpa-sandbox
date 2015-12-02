package io.chanwook.jpa;

import io.chanwook.jpa.entity.Product;
import io.chanwook.jpa.repository.ProductJpaRepository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

/**
 * @author chanwook
 */
public class ProductTestUtils {
    public static void createTestProduct(ProductJpaRepository r, int max) {
        createTestProduct(r, max, LocalDateTime.now());
    }

    public static void createTestProduct(ProductJpaRepository r, int max, LocalDateTime time) {
        IntStream.range(0, max).forEach(row -> {
            // each save to simple test...
            r.save(new Product("PRD-" + String.valueOf(row), "Good Product~", time, time));
        });
    }
}
