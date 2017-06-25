package io.chanwook.jpa.service;

import io.chanwook.jpa.entity.Asset;
import io.chanwook.jpa.repository.AssetJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chanwook
 */
@Service
public class AssetService {

    @Autowired
    AssetJpaRepository assetRepository;

    @Transactional
    public void save(Asset newAsset) {
        assetRepository.save(newAsset);
    }
}
