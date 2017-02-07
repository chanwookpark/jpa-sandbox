package io.chanwook.jpa.repository;

import io.chanwook.jpa.entity.MemberAsset;
import io.chanwook.jpa.entity.MemberAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author chanwook
 */
public interface MemberAssetRepository extends JpaRepository<MemberAsset, MemberAssetId>, QueryDslPredicateExecutor<MemberAsset> {

    @Query("SELECT ma FROM MemberAsset ma WHERE ma.asset.assetId = ?1 and ma.member.memberId = ?2")
    MemberAsset findOne(long assetId, String memberId);

    @Query("SELECT ma.balance FROM MemberAsset ma WHERE ma.asset.assetId = ?1 and ma.member.memberId = ?2")
    long findAssetBalance(long assetId, String memberId);
}
