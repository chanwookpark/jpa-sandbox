package io.chanwook.jpa.repository;

import io.chanwook.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * @author chanwook
 */
public interface ProductJpaRepository extends JpaRepository<Product, String> {

    Product findByEnrolledAndUpdated(LocalDateTime enrolled, LocalDateTime updated);

    default Optional<Product> findByEnrolledAndUpdated(Product p) {
        final Product result = this.findByEnrolledAndUpdated(p.getEnrolled(), p.getUpdated());
        return p == null ? Optional.empty() : Optional.of(result);
    }

    Optional<Product> findOneByProductId(String productId);

    @Query("select p from Product p")
    Stream<Product> findAllByCustomQueryWithStream();

    @Async
    @Query("select p from Product p")
    CompletableFuture<List<Product>> readAllBy();

}
