package com.springwheel.data.repository;

import com.springwheel.data.domain.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/27
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public interface DeviceDao extends JpaRepository<Device, Long>, JpaSpecificationExecutor<Device> {

    Page<Device> findAll(Specification<Device> spec, Pageable page);
}
