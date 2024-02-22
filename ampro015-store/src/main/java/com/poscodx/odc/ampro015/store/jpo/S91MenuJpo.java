package com.poscodx.odc.ampro015.store.jpo;

import com.poscdx.odc.ampro015.domain.entity.S91Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "S91Menu")
@Table(name = "TB_S91_MENU", schema = "VIVA-ODC")
public class S91MenuJpo implements Serializable {

    @Id
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "FORM_MNU_EXPL")
    private String formMenuExplain;
    @Column(name = "URL")
    private String url;

    public S91MenuJpo(S91Menu domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }

    public S91Menu toDomain() {
        S91Menu domainEntity = new S91Menu();
        BeanUtils.copyProperties(this, domainEntity);
        return domainEntity;
    }

    public static List<S91Menu> toDomains(Iterable<S91MenuJpo> jpos) {
        return StreamSupport.stream(jpos.spliterator(), false).map(S91MenuJpo::toDomain).collect(Collectors.toList());
    }

}
