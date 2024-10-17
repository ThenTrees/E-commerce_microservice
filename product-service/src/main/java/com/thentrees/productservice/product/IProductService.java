package com.thentrees.productservice.product;

import java.util.List;

public interface IProductService {
    Integer createProduct(ProductRequest productRequest);
    ProductResponse findById(Integer id);
    List<ProductResponse> findAll();
    List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request
    );

}
