package com.thentrees.productservice.product;

import com.thentrees.productservice.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        var product = mapper.toProduct(productRequest);
        return repository.save(product).getId();
    }

    @Override
    public ProductResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
    }

    @Override
    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    @Transactional(rollbackFor = ProductPurchaseException.class)
    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request.stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists!!!");
        }
        var sortedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.getProductId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product); // cos the dua vao 1 arraylist khac roi save all de optimize
            purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.getQuantity()));
        }
        return purchasedProducts;
    }
}
