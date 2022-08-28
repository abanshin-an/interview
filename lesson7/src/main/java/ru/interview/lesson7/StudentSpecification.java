package ru.interview.lesson7;

import org.springframework.data.jpa.domain.Specification;

public final class StudentSpecification {
    private StudentSpecification() {
        throw new IllegalStateException("Utility class");
    }
    public static Specification<Student> nameLike(String pattern) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + pattern + "%");
    }

    public static Specification<Student> minAgeFilter(Integer minAge) {
        return (root, query, builder) -> builder.ge(root.get("age"), minAge);
    }

    public static Specification<Student> maxAgeFilter(Integer maxAge) {
        return (root, query, builder) -> builder.le(root.get("age"), maxAge);
    }
}
