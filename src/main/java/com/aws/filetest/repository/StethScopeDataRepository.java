package com.aws.filetest.repository;

import com.aws.filetest.model.StethScopeData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * StethScopeDataRepository
 *
 * @author user
 * @version 0.1
 * @see
 * @since 2021-12-24
 */
public interface StethScopeDataRepository extends JpaRepository<StethScopeData, Long> {
}
