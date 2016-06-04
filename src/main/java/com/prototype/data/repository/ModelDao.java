package com.prototype.data.repository;

import com.prototype.data.domain.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/21
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public interface ModelDao extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Model> {

    Page<Model> findAll(Specification<Model> spec, Pageable page);
}
