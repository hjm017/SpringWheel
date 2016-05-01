package com.yk.service;

import com.yk.common.constants.YKConstant;
import com.yk.controller.form.device.DeviceSearchForm;
import com.yk.controller.form.site.SiteSearchForm;
import com.yk.domain.Device;
import com.yk.domain.Site;
import com.yk.repository.DeviceDao;
import com.yk.repository.SiteDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/25
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DeviceService {
    private static Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    private DeviceDao     deviceDao;

    @Transactional(readOnly = true)
    public Page<Device> findAll(DeviceSearchForm deviceSearchForm) {
        deviceSearchForm.setPageSize(YKConstant.PAGE_SIZE);
        int currentPage = (deviceSearchForm.getCurrentPage() == 0) ? 0
            : (deviceSearchForm.getCurrentPage() - 1);
        return deviceDao.findAll(getWhereClause(deviceSearchForm),
            new PageRequest(currentPage, deviceSearchForm.getPageSize()));
    }

    private Specification<Device> getWhereClause(final DeviceSearchForm deviceSearchForm) {
        return new Specification<Device>() {
            @Override
            public Predicate toPredicate(Root<Device> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtils.isNotBlank(deviceSearchForm.getKeyword())) {
                    predicate.getExpressions().add(cb.like(r.<String> get("siteName"),
                        "%" + StringUtils.trim(deviceSearchForm.getKeyword()) + "%"));
                }
                if (StringUtils.isNotBlank(deviceSearchForm.getRegion())) {
                    predicate.getExpressions()
                        .add(cb.equal(r.<String> get("region"), deviceSearchForm.getRegion()));
                }
                if (StringUtils.isNotBlank(deviceSearchForm.getStatus())) {
                    predicate.getExpressions()
                        .add(cb.equal(r.<String> get("status"), deviceSearchForm.getStatus()));
                }
                if (StringUtils.isNotBlank(deviceSearchForm.getDeviceNo())) {
                    predicate.getExpressions()
                        .add(cb.equal(r.<String> get("deviceNo"), deviceSearchForm.getDeviceNo()));
                }

                return predicate;
            }
        };
    }

}
