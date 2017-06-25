package io.chanwook.jpa.entity;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.chanwook.jpa.App;
import io.chanwook.jpa.repository.AssetJpaRepository;
import io.chanwook.jpa.service.AssetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chanwook
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
public class EntityMappingTest {

    @Autowired
    AssetService assetService;

    @Autowired
    AssetJpaRepository assetRepository;

    @Test
    public void long타입의NullValue문제확인() throws Exception {
        final Asset withAmount = new Asset("포인트1", 100);
        final Asset noAmount = new Asset("포인트2");

        assetService.save(withAmount);
        assetService.save(noAmount);

        assert assetRepository.findOne(withAmount.getAssetId()).getAssetName().equals(withAmount.getAssetName());
        assert assetRepository.findOne(noAmount.getAssetId()).getAssetName().equals(noAmount.getAssetName());

        final BooleanExpression eq = QAsset.asset.assetName.eq(noAmount.getAssetName());
        final Asset assetWithQdsl = assetRepository.findOne(eq);

        assert assetWithQdsl.getCurrentAmount() == 0;

    }
}
