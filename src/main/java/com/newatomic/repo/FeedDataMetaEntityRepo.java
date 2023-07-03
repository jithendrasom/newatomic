package com.newatomic.repo;

import com.newatomic.model.FeedDataMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeedDataMetaEntityRepo extends JpaRepository<FeedDataMetaEntity, Long> {
    List<FeedDataMetaEntity> findAllByInstrumentToken(Long token);

    List<FeedDataMetaEntity> findAllByTradingSymbolAndRecordDate(String tradingSymbol, LocalDate recordDate);
}