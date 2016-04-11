package com.github.repository;

import com.github.entity.Ap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by zk_chs on 16/4/11.
 */
@Repository
public interface ApRepository extends JpaRepository<Ap, Integer>, JpaSpecificationExecutor<Ap> {
}
