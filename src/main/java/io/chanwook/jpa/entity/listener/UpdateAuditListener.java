package io.chanwook.jpa.entity.listener;

import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * 엔티티 수정 이벤트를 잡아서 수정일시를 DB에 자동 기록
 *
 * @author chanwook
 */
@MappedSuperclass
@Data
public abstract class UpdateAuditListener {

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @Column(name = "UPDAT_DAT")
    protected LocalDateTime updateDateTime;

    @PreUpdate
    protected void onUpdate() {
        this.updateDateTime = LocalDateTime.now();
    }
}
