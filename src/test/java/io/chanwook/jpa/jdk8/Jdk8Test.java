package io.chanwook.jpa.jdk8;

import io.chanwook.jpa.App;
import io.chanwook.jpa.entity.Product;
import io.chanwook.jpa.repository.ProductJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static io.chanwook.jpa.ProductTestUtils.createTestProduct;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;

/**
 * @author chanwook
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {App.class})
@Transactional
public class Jdk8Test {

    private final Logger logger = LoggerFactory.getLogger(Jdk8Test.class);

    @Autowired
    ProductJpaRepository r;

    @Test
    public void convertLocalDateTime() throws Exception {

        final LocalDateTime enrolled = of(2015, Month.JANUARY, 1, 6, 30);
        final LocalDateTime updated = of(2015, Month.JANUARY, 2, 10, 00);
        Product created = new Product("new001", "Good Product with Big Sale!!!", enrolled, updated);

        r.save(created);

        Product searched = r.findByEnrolledAndUpdated(enrolled, updated);

        assert searched != null;
        assert searched.getEnrolled().equals(enrolled);
        assert searched.getUpdated().equals(updated);

    }

    @Test
    public void optional() throws Exception {

        r.save(new Product("yes-id", "Good Product with Big Sale!!!", now(), now()));

        Optional<Product> searched = r.findOneByProductId("no-id");

        assert searched != null;
        assert searched.isPresent() == false;
    }

    @Test
    public void defaultMethod() throws Exception {
        final LocalDateTime enrolled = of(2015, Month.JANUARY, 1, 6, 30);
        final LocalDateTime updated = of(2015, Month.JANUARY, 2, 10, 00);
        Product created = new Product("new001", "Good Product with Big Sale!!!", enrolled, updated);

        r.save(created);

        Optional<Product> searched = r.findByEnrolledAndUpdated(created);

        assert searched != null;
        assert searched.get().getEnrolled().equals(enrolled);
        assert searched.get().getUpdated().equals(updated);
    }

    @Test
    public void stream() throws Exception {
        createTestProduct(r, 10);

        try (Stream<Product> stream = r.findAllByCustomQueryWithStream()) {
            assert stream.map(Product::getProductId).allMatch(id -> id.startsWith("PRD-"));
        }
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void async() throws Exception {
        final int size = 10;
        createTestProduct(r, size);

        r.flush(); // sync db

        final CompletableFuture<Void> future = r.readAllBy().thenAccept(products -> {
            assert size == products.size();
            logger.debug(">> Complete!!");
        });

        while (!future.isDone()) {
            logger.debug(">> Waiting...");
            Thread.sleep(500);
        }

        future.get();

        logger.debug(">> End!!");

        r.deleteAllInBatch();
    }

}
