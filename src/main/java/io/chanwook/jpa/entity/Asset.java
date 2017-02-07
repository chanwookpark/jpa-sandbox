package io.chanwook.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author chanwook
 */
@Entity
@Data
@NoArgsConstructor
public class Asset {

    @Id
    @GeneratedValue
    private long assetId;

    @Column
    private String assetName;

    public Asset(String assetName) {
        this.assetName = assetName;
    }

}
