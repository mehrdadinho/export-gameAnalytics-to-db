package com.gameanalytics.export.repository;

import com.gameanalytics.export.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevkumarsingh on 20/11/17.
 */
@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

}
