package io.chanwook.jpa.querydsl;

import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.expr.BooleanExpression;
import io.chanwook.jpa.App;
import io.chanwook.jpa.entity.Product;
import io.chanwook.jpa.entity.QProduct;
import io.chanwook.jpa.repository.ProductJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.querydsl.QSort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static io.chanwook.jpa.ProductTestUtils.createTestProduct;
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
        final LocalDateTime now = now();
        createTestProduct(r, 3, now);

        final BooleanExpression where = QProduct.product.productId.eq("PRD-1");
        final Product p = r.findOne(where);

        assert p != null;

        final BooleanExpression expression = QProduct.product.enrolled.eq(now).and(QProduct.product.updated.eq(now));
        final List<Product> list = (List<Product>) r.findAll(expression);

        assert 3 == list.size();
    }


    @Transactional
    @Test
    public void ordering() throws Exception {
        createTestProduct(r, 3);

        // if single order statement, using OrderSpecifier directly
        final List<Product> list = (List<Product>) r.findAll(QProduct.product.productId.like("PRD-%"),
                new QSort(new OrderSpecifier<>(Order.DESC, QProduct.product.productId)));

        assert 3 == list.size();
        assert "PRD-2".equals(list.get(0).getProductId());
        assert "PRD-1".equals(list.get(1).getProductId());
        assert "PRD-0".equals(list.get(2).getProductId());
    }

}
