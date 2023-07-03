package com.newatomic.repo;

import com.newatomic.model.FeedDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*

@Repository
public interface FeedDataEntityRepo extends JpaRepository<FeedDataEntity, Long> {
    List<FeedDataEntity> findByFeedDataMetaId(Long metaDataId);
}*/

public interface FeedDataEntityRepo {}