package io.chanwook.jpa.querydsl;

import com.mysema.query.types.expr.BooleanExpression;
import io.chanwook.jpa.App;
import io.chanwook.jpa.entity.Product;
import io.chanwook.jpa.entity.QProduct;
import io.chanwook.jpa.repository.ProductJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static java.time.LocalDateTime.now;

/**
 * @author chanwook
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {App.class})
public class QueryDslTest {

    @Autowired
    ProductJpaRepository r;

    @Transactional
    @Test
    public void simple() throws Exception {
        r.save(new Product("prd001", "Good Product~", now(), now()));

        final BooleanExpression where = QProduct.product.productId.eq("prd001");
        final Product p = r.findOne(where);

        assert p != null;
    }
}
