package es.com.between.specification;

import es.com.between.entity.PricesEntity;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Objects;

public class PricesSpecification {

    private PricesSpecification() {}

    private static final String BRAND_ID = "id";
    private static final String PRODUCT_ID = "id";
    private static final String BRAND = "brand";
    private static final String PRODUCT = "product";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String PRIORITY = "priority";

    public static Specification<PricesEntity> findPriceByCriteria(LocalDateTime consultationDate,
                                                                  Long brandId, Long productId) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<PricesEntity> subqueryRoot = subquery.from(PricesEntity.class);

            subquery.select(criteriaBuilder.max(subqueryRoot.get(PRIORITY)));
            subquery.where(
                    criteriaBuilder.equal(subqueryRoot.get(BRAND).get(BRAND_ID), brandId),
                    criteriaBuilder.equal(subqueryRoot.get(PRODUCT).get(PRODUCT_ID), productId),
                    criteriaBuilder.lessThanOrEqualTo(subqueryRoot.get(START_DATE), consultationDate),
                    criteriaBuilder.greaterThanOrEqualTo(subqueryRoot.get(END_DATE), consultationDate)
            );

            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get(BRAND).get(BRAND_ID), brandId),
                    criteriaBuilder.equal(root.get(PRODUCT).get(PRODUCT_ID), productId),
                    criteriaBuilder.lessThanOrEqualTo(root.get(START_DATE), consultationDate),
                    criteriaBuilder.greaterThanOrEqualTo(root.get(END_DATE), consultationDate),
                    criteriaBuilder.equal(root.get(PRIORITY), subquery)
            );
        };
    }
}
