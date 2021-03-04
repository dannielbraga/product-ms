package com.compasso.productms.domain.repository.specification;

import com.compasso.productms.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class ProductNameDescriptionSpecification implements Specification<Product> {
    private final String q;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (q == null){
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        else {
            return criteriaBuilder.or(criteriaBuilder.like(root.<String>get("name"), "%" + this.q + "%"),
                                      criteriaBuilder.like(root.<String>get("description"), "%" + this.q + "%"));
        }
    }
}
