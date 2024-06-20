package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dao.ProductDAO;
import com.qlyshopphone_backend.dto.ProductDTO;
import com.qlyshopphone_backend.dto.ProductImageDTO;
import com.qlyshopphone_backend.exceptions.DataNotFoundException;
import com.qlyshopphone_backend.exceptions.InvalidParamException;
import com.qlyshopphone_backend.model.*;
import com.qlyshopphone_backend.repository.*;
import com.qlyshopphone_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ProductServiceImpl extends BaseReponse implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private GroupProductRepository groupProductRepository;
    @Autowired
    private TrademarkRepository trademarkRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private PropertiesRepository propertiesRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductDAO productDAO;

    @Override
    public ResponseEntity<?> getAllProducts() {
        return getResponseEntity(productRepository.getAllProducts());
    }

    @Override
    public Product saveProduct(ProductDTO productDTO) throws Exception {
        GroupProduct existingGroupProduct = groupProductRepository.findById(productDTO.getGroupProductId())
                .orElseThrow(() -> new DataNotFoundException("Can't find product with id: " + productDTO.getGroupProductId()));
        Trademark existingTrademark = trademarkRepository.findById(productDTO.getTrademarkId())
                .orElseThrow(() -> new DataNotFoundException("Can't find trademark with id: " + productDTO.getTrademarkId()));
        Location existingLocation = locationRepository.findById(productDTO.getLocationId())
                .orElseThrow(() -> new DataNotFoundException("Can't find product with id: " + productDTO.getLocationId()));
        Properties existingProperties = propertiesRepository.findById(productDTO.getPropertiesId())
                .orElseThrow(() -> new DataNotFoundException("Can't find product with id: " + productDTO.getPropertiesId()));
        Unit existingUnit = unitRepository.findById(productDTO.getUnitId())
                .orElseThrow(() -> new DataNotFoundException("Can't find unit with id: " + productDTO.getUnitId()));
        Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Can't find unit with id: " + productDTO.getCategoryId()));


        Product product = new Product();
        product.setProductId(product.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setCapitalPrice(productDTO.getCapitalPrice());
        product.setInventory(productDTO.getInventory());
        product.setGroupProduct(existingGroupProduct);
        product.setLocation(existingLocation);
        product.setTrademark(existingTrademark);
        product.setWeight(productDTO.getWeight());
        product.setProperties(existingProperties);
        product.setUnit(existingUnit);
        product.setActive(productDTO.getActive());
        product.setCategory(existingCategory);
        product.setDirectSales(productDTO.getDirectSales());
        productRepository.save(product);
        return productRepository.save(product);

    }

    @Override
    public ResponseEntity<?> findByIdProduct(int productId) {
        return ResponseEntity.ok().body(productRepository.findById(productId));
    }

    @Override
    public Optional<Product> findByProductId(int productId) {
        return productRepository.findById(productId);
    }

    @Override
    public ResponseEntity<?> deleteProduct(int productId) {
        try {
            productDAO.deleteProduct(productId);
            return getResponseEntity("Product with id: " + productId + " deleted successfully");
        }catch (Exception e) {
            return getResponseEntity("Can't delete product with id: " + productId);
        }
    }

    @Override
    public ResponseEntity<?> searchAllByProductName(String productName) {
        List<Map<String, Object>> allProducts = productRepository.searchAllByProductName(productName);
        if (allProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found");
        } else {
            return getResponseEntity(allProducts);
        }
    }

    @Override
    public ResponseEntity<?> searchAllByProductId(int productId) {
        return getResponseEntity(productRepository.searchAllByProductId(productId));
    }

    @Override
    public ProductImage saveProductImage(ProductImageDTO productImageDTO, int productId) throws Exception {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException
                        ("Cannot find product with id: " + productImageDTO.getProductId()));
        ProductImage newProductImage = new ProductImage();
        newProductImage.setProduct(existingProduct);
        newProductImage.setImageUrl(productImageDTO.getImageUrl());
        int size = productImageRepository.findByProductId(productId).size();
        if (size > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new InvalidParamException("Number of images must be <= 5 ");
        }
        return productImageRepository.save(newProductImage);
    }

    @Override
    public Product getProductById(int productId) throws Exception {
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
    }

    @Override
    public boolean existsByProductName(String name) {
        return productRepository.existsByProductName(name);
    }

    @Override
    public boolean existsByImageUrl(String imageUrl) {
        return productImageRepository.existsByImageUrl(imageUrl);
    }

    @Override
    public ResponseEntity<?> updateProductImageUrls() {
        try {
            productImageRepository.updateProductImageUrls();
            return getResponseEntity("Product image updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> searchGroupProductId(int groupProductId) {
        return getResponseEntity(productRepository.searchGroupProductId(groupProductId));
    }

    @Override
    public ResponseEntity<?> searchInventory(int number) {
        switch (number) {
            case 1:
                // Dưới định mức tồn
                return getResponseEntity(productRepository.searchBelowInventoryThreshold());
            case 2:
                // Vượt định mức tồn
                return getResponseEntity(productRepository.searchExceedingInventoryLimit());
            case 3:
                // Còn hàng trong kho
                return getResponseEntity(productRepository.searchStockAvailable());
            case 4:
                // Hết hàng trong kho
                return getResponseEntity(productRepository.searchNoInventoryAvailable());
            default:
                return getResponseEntity("Search Inventory failed");
        }
    }

    @Override
    public ResponseEntity<?> searchActive(int number) {
        switch (number) {
            case 1:
                // Hàng đang kinh doanh
                return getResponseEntity(productRepository.getAllProducts());
            case 2:
                // Hàng ngưng kinh doanh
                return getResponseEntity(productRepository.searchNoActive());
            case 3:
                // Tất cả hàng
                return getResponseEntity(productRepository.searchActive());
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid active number");
        }
    }

    @Override
    public ResponseEntity<?> searchDirectSales(int number) {
        switch (number) {
            case 1:
                // Tất cả
                return getResponseEntity(productRepository.getAllProducts());
            case 2:
                // Được bán trực tiếp
                return getResponseEntity(productRepository.searchDirectSales());
            case 3:
                // Không bán trực tiếp
                return getResponseEntity(productRepository.searchNoDirectSales());
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid active number");
        }
    }

    @Override
    public ResponseEntity<?> searchByLocationId(int locationId) {
        return getResponseEntity(productRepository.searchByLocationId(locationId));
    }

    @Override
    public ResponseEntity<?> searchCategory(int number) {
        switch (number) {
            case 1:
                return getResponseEntity(productRepository.searchCategory1());
            case 2:
                return getResponseEntity(productRepository.searchCategory2());
            case 3:
                return getResponseEntity(productRepository.searchCategory3());
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid category number");
        }
    }

    @Override
    public Map<String, Object> getProductDetailId(int productId) {
        return productRepository.getDetailProductId(productId);
    }
}
