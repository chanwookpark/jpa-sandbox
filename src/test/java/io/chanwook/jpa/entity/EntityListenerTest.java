package io.chanwook.jpa.entity;

import io.chanwook.jpa.App;
import io.chanwook.jpa.repository.OrderJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * @author chanwook
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
public class EntityListenerTest {

    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Test
    public void 엔티티리스너를사용해생성수정일시설정하기() throws Exception {

        Order order = new Order(1000);

        orderJpaRepository.save(order);

        // 첫 저장(persist) 시에는 빈값
        assert null == order.getUpdateDateTime();

        // update하고 update 필드 확인
        order.setOrderTotalAmount(2000);
        final Order updated = orderJpaRepository.save(order);

        //정확한 시간 값은 알수 없지만...
        assert null != updated.getUpdateDateTime();
        assert updated.getUpdateDateTime().isBefore(LocalDateTime.now());
    }
}
