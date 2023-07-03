package com.newatomic.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "feed_data_meta")
public class FeedDataMetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feed_data_meta_id")
    private Long feedDataMetaId;

    @Column(name = "trading_symbol")
    private String tradingSymbol;

    @Column(name = "instrument_token")
    private String instrumentToken;

    @Column(name = "record_date")
    private LocalDate recordDate;


    public FeedDataMetaEntity(Long id, String tradingSymbol, String instrumentToken, LocalDate recordDate) {
        this.feedDataMetaId = id;
        this.tradingSymbol = tradingSymbol;
        this.instrumentToken = instrumentToken;
        this.recordDate = recordDate;
    }

    public FeedDataMetaEntity() {
    }
}
