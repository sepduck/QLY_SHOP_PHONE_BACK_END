package com.qlyshopphone_backend.controller;

import com.qlyshopphone_backend.dto.ProductDTO;
import com.qlyshopphone_backend.dto.ProductImageDTO;
import com.qlyshopphone_backend.model.Product;
import com.qlyshopphone_backend.model.ProductImage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ControllerDemo {

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_KETOAN', 'ROLE_NHANVIEN')")
//    @PostMapping(value = "/product")
//    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO,
//                                        BindingResult result) {
//        try {
//            if (result.hasErrors()) {
//                List<String> errorMessages = result.getFieldErrors()
//                        .stream()
//                        .map(FieldError::getDefaultMessage)
//                        .toList();
//                return ResponseEntity.badRequest().body(errorMessages);
//            }
//            groupProductService.getAllGroupProduct();
//            return ResponseEntity.ok(productService.saveProduct(productDTO));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> uploadFile(@PathVariable("id") int id,
//                                        @ModelAttribute("file") List<MultipartFile> files) {
//        try {
//            Product existingProduct = productService.getProductById(id);
//            files = files == null ? new ArrayList<MultipartFile>() : files;
//            if (files.size() > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
//                return ResponseEntity.badRequest().body("You can only upload 5 files");
//            }
//            List<ProductImage> productImages = new ArrayList<>();
//            for (MultipartFile file : files) {
//                // Bỏ qua nếu file mà không có ảnh
//                if (file.getSize() == 0) {
//                    continue;
//                }
//                // Kiểm tra kích thước ảnh nhỏ hơn 10MB
//                if (file.getSize() > 10 * 1024 * 1024) {
//                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
//                            .body("File is too large! Max size is 10MB");
//                }
//                // Kiểm tra có phải là ảnh không ?
//                String contentType = file.getContentType();
//                if (contentType == null || !contentType.startsWith("image/")) {
//                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//                            .body("File must be an image");
//                }
//                String fileName = storeFile(file);
//                ProductImage productImage = productService.saveProductImage(
//                        ProductImageDTO.builder().imageUrl(fileName).build(),
//                        existingProduct.getProductId());
//                productImages.add(productImage);
//            }
//            return ResponseEntity.ok().body(productImages);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    // Nơi lưu file ảnh
//    private String storeFile(MultipartFile file) throws IOException {
//        if (!isImageFile(file) || file.getOriginalFilename() == null) {
//            throw new IOException("Invalid image file");
//        }
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        // Thêm thư viện UUID vào trước file để đảm bảo tên file là duy nhất
//        String uniqueFilename = UUID.randomUUID().toString() + "_" + fileName;
//        // Đường dẫn đến thư mục muốn lưu file
//        Path uploadDir = Paths.get("uploads");
//        // Kiểm tra và tạo thư mục nếu nó tồn tại
//        if (!Files.exists(uploadDir)) {
//            Files.createDirectory(uploadDir);
//        }
//        // Đường dẫn đầy đủ đến file
//        Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
//        // Sao chép file đến thư mục đích
//        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
//        return uniqueFilename;
//    }
//
//    private boolean isImageFile(MultipartFile file) {
//        String contentType = file.getContentType();
//        return contentType != null && contentType.startsWith("image/");
//    }
}
