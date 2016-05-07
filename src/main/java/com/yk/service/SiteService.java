package com.yk.service;

import com.yk.common.constants.YKConstant;
import com.yk.controller.form.site.SiteSearchForm;
import com.yk.data.domain.Site;
import com.yk.data.repository.SiteDao;
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

import javax.persistence.criteria.*;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/25
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SiteService {
    private static Logger logger = LoggerFactory.getLogger(SiteService.class);

    @Autowired
    private SiteDao       siteDao;

    @Transactional(readOnly = true)
    public Iterable<Site> findAll(Pageable pageable) {
        return siteDao.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Site> findAll(SiteSearchForm siteSearchForm) {
        siteSearchForm.setPageSize(YKConstant.PAGE_SIZE);
        int currentPage = (siteSearchForm.getCurrentPage() == 0) ? 0
            : (siteSearchForm.getCurrentPage() - 1);
        return siteDao.findAll(getWhereClause(siteSearchForm),
            new PageRequest(currentPage, siteSearchForm.getPageSize()));
    }

    private Specification<Site> getWhereClause(final SiteSearchForm siteSearchForm) {
        return new Specification<Site>() {
            @Override
            public Predicate toPredicate(Root<Site> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtils.isNotBlank(siteSearchForm.getKeyword())) {
                    predicate.getExpressions().add(cb.like(r.<String> get("siteName"),
                        "%" + StringUtils.trim(siteSearchForm.getKeyword()) + "%"));
                }
                if (StringUtils.isNotBlank(siteSearchForm.getRegion())) {
                    predicate.getExpressions()
                        .add(cb.equal(r.<String> get("region"), siteSearchForm.getRegion()));
                }
                predicate.getExpressions().add(cb.equal(r.<String> get("isDelete"), 0));

                return predicate;
            }
        };
    }

    @Transactional(readOnly = true)
    public Site findOne(Long id) {
        return siteDao.findOne(id);
    }
}
