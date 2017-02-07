package io.chanwook.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author chanwook
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String memberId;

}
