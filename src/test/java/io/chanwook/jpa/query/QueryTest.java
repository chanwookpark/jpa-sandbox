package io.chanwook.jpa.query;

import io.chanwook.jpa.App;
import io.chanwook.jpa.entity.Asset;
import io.chanwook.jpa.entity.Member;
import io.chanwook.jpa.entity.MemberAsset;
import io.chanwook.jpa.repository.MemberAssetRepository;
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
public class QueryTest {

    @Autowired
    MemberAssetRepository r;

    @Test
    public void test1() throws Exception {

        Member member = new Member("test01");
        Asset asset = new Asset("대박마일리지");
        MemberAsset memberAsset = new MemberAsset(asset, member, 5000);

        r.save(memberAsset);

        assert 1 == r.count();

        // Query가 총 3번 실행됨. MemberAsset -> Asset과 Member가 Eager라서..
        final MemberAsset result = r.findOne(asset.getAssetId(), member.getMemberId());

        assert result != null;
        assert memberAsset.getBalance() == result.getBalance();

        // Query 1번 실행됨
        final long balance = r.findAssetBalance(asset.getAssetId(), member.getMemberId());
        assert memberAsset.getBalance() == balance;
    }
}
