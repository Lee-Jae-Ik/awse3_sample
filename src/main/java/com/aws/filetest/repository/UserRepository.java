package com.aws.filetest.repository;

import com.aws.filetest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 *
 * @author user
 * @version 0.1
 * @see
 * @since 2021-12-24
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
