package io.chanwook.jpa.repository;

import io.chanwook.jpa.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author chanwook
 */
public interface AssetJpaRepository extends JpaRepository<Asset, Long>, QueryDslPredicateExecutor<Asset> {
}
