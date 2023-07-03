package com.newatomic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;


@Data
@Builder
@Entity
@Table(name = "feed_data")
public class FeedDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feed_data_id")
    private Long feedDataId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_data_meta_id", nullable = false)
    @JsonIgnore
    private FeedDataMetaEntity feedDataMetaId;

    @Column(name = "value")
    private Double value;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "seconds")
    private Integer seconds;

    public FeedDataEntity(Long feedDataId, FeedDataMetaEntity feedDataMeta, Double value, Integer hours, Integer minutes, Integer seconds ) {
        this.feedDataId = feedDataId;
        this.value = value;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.feedDataMetaId = feedDataMeta;
    }

    public FeedDataEntity() {
    }
}
