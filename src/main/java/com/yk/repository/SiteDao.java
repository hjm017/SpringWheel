package com.yk.repository;

import com.yk.domain.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/25
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
public interface SiteDao extends JpaRepository<Site, Long>, JpaSpecificationExecutor<Site> {

    //    @Query(value = "SELECT x FROM User x WHERE x.name LIKE :name% ORDER BY x.id", countQuery = "SELECT COUNT(x) FROM User x WHERE x.name LIKE :name%")
    //    Page<User> findByNameLike(@Param("name") String name, Pageable page);

    //    Page<Site> findAll(Specification<Site> spec, Pageable page);

    Page<Site> findAll(Specification<Site> spec, Pageable page);
}
