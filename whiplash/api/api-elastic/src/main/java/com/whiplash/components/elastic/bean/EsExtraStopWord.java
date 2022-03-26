package com.whiplash.components.elastic.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/12/7 14:49
 * @projectName whiplash
 * @title: EsExtraStopWord
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "es_extra_stopword")
public class EsExtraStopWord implements Serializable {
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
