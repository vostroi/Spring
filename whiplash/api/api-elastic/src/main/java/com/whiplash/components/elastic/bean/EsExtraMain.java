package com.whiplash.components.elastic.bean;

import com.whiplash.core.platform.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/12/7 14:49
 * @projectName whiplash
 * @title: EsExtMain
 * @description: elastic 停用词 实体
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "es_extra_main")
public class EsExtraMain implements Serializable {

    @Id
    @Column(name="id" , columnDefinition = "bigint UNSIGNED NOT NULL AUTO_INCREMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word" , columnDefinition = "VARCHAR(256)")
    private String word;

    @Column(name = "is_deleted" , columnDefinition = "tinyint")
    private boolean deleted;

    @Column(name = "update_time" )
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}
