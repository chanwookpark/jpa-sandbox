package io.chanwook.jpa.entity.listener;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * 엔티티 생성 시에 생성 일시를 자동으로 저장하기 위한 리스너 클래스
 *
 * @author chanwook
 */
public class CreateAuditListener {

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @Column(name = "CRT_DAT")
    protected LocalDateTime createDateTime;

    /**
     * 외부 listener로 구현 시에는 메서드 인자로 엔티티를 받게 된다
     *
     * @param entity
     */
    @PrePersist
    public void onPersist(Object entity) {
        createDateTime = LocalDateTime.now();
    }
}
