package com.yk.service;

import com.yk.common.constants.YKConstant;
import com.yk.controller.form.model.ModelSearchForm;
import com.yk.domain.Model;
import com.yk.repository.ModelDao;
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
 * Date: 2016/4/21
 * Time: 17:03
 * To change this template use File | Settings | File Templates.
 */
// Spring Bean的标识.
@Service
public class ModelService {

    private static Logger logger = LoggerFactory.getLogger(ModelService.class);

    @Autowired
    private ModelDao modelDao;

    @Transactional(readOnly = true)
    public Iterable<Model> findAll(Pageable pageable) {
        return modelDao.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Model findOne(Long id) {
        return modelDao.findOne(id);
    }

    @Transactional(readOnly = true)
    public Page<Model> findAll(ModelSearchForm modelSearchForm) {
        modelSearchForm.setPageSize(YKConstant.PAGE_SIZE);
        int currentPage = (modelSearchForm.getCurrentPage() == 0) ? 0
            : (modelSearchForm.getCurrentPage() - 1);
        return modelDao.findAll(getWhereClause(modelSearchForm),
            new PageRequest(currentPage, modelSearchForm.getPageSize()));
    }

    private Specification<Model> getWhereClause(final ModelSearchForm modelSearchForm) {
        return new Specification<Model>() {
            @Override
            public Predicate toPredicate(Root<Model> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(r.<String> get("isDelete"), 0));

                return predicate;
            }
        };
    }
}
