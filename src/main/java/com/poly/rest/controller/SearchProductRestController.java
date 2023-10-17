package com.poly.rest.controller;


import com.poly.dao.ProductDAO;
import com.poly.entity.Category;
import com.poly.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class SearchProductRestController {

    @Autowired
    ProductDAO productRepository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchList(@RequestParam("name") String name) {
        try {
            if (name == null || name.isEmpty()) {
                return ResponseEntity.badRequest().build(); // Trả về mã lỗi 400 Bad Request nếu name không hợp lệ.
            }

            List<Product> products = productRepository.findProductsByName(name);

            if (products.isEmpty()) {
                return ResponseEntity.noContent().build(); // Trả về mã lỗi 204 No Content nếu không có sản phẩm nào được tìm thấy.
            }

            return ResponseEntity.ok(products); // Trả về danh sách sản phẩm nếu tìm kiếm thành công.
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về mã lỗi 500 Internal Server Error nếu có lỗi xảy ra.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/filter")
    public List<Product> findProductsByFilter(Double minPrice, Double maxPrice, Integer IdCategory) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        // Tạo danh sách các điều kiện để thêm vào truy vấn
        List<Predicate> predicates = new ArrayList<>();

        // Kiểm tra và thêm điều kiện về giá
        if (minPrice != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("Price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("Price"), maxPrice));
        }

        // Kiểm tra và thêm điều kiện về danh mục
        if (IdCategory != null) {
            Join<Product, Category> categoryJoin = root.join("category");
            predicates.add(criteriaBuilder.equal(categoryJoin.get("IdCategory"), IdCategory));
        }

        // Nếu không cung cấp giá hoặc danh mục, trả về tất cả sản phẩm
        if (minPrice == null && maxPrice == null && IdCategory == null) {
            return entityManager.createQuery(query).getResultList();
        }

        // Kết hợp tất cả các điều kiện bằng AND
        if (!predicates.isEmpty()) {
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }

        return entityManager.createQuery(query).getResultList();
    }


}

