package io.chanwook.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author chanwook
 */
@Entity
@Data
@NoArgsConstructor
@IdClass(MemberAssetId.class)
public class MemberAsset {

    @Id
    @ManyToOne
    @JoinColumn(name = "ASSET_ID", nullable = false)
    private Asset asset;

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    private long balance;

    public MemberAsset(Asset asset, Member member, long balance) {
        this.asset = asset;
        this.member = member;
        this.balance = balance;
    }
}
