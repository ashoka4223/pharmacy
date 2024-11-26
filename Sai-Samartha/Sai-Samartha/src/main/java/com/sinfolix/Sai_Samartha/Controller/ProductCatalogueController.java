package com.sinfolix.Sai_Samartha.Controller;

import com.sinfolix.Sai_Samartha.DTO.ApiResponse;
import com.sinfolix.Sai_Samartha.DTO.ProductCatalogueDTO;
import com.sinfolix.Sai_Samartha.Entities.ProductCatalogue;
import com.sinfolix.Sai_Samartha.Service.ProductCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductCatalogueController {


    @Autowired
    ProductCatalogueService productCatalogueService;

    // Admin will ADD/CREATE the new product to website

    @PostMapping("/")
    public ResponseEntity<ProductCatalogueDTO> createProductCatalogue(@RequestBody ProductCatalogueDTO productCatalogueDTO){
        System.out.println("Hello Ashok");
        ProductCatalogueDTO createproductCatalogueDTO = this.productCatalogueService.createProductCatalogue(productCatalogueDTO);
        return new ResponseEntity<>(createproductCatalogueDTO, HttpStatus.CREATED);
    }

    // Admin will UPDATE the existing product

    @PutMapping("/{id}")
    public ResponseEntity<ProductCatalogueDTO> updateProductCatalogue(@PathVariable Long id, @RequestBody ProductCatalogueDTO productCatalogueDTO){
        ProductCatalogueDTO updatedProductCatalogueDTO = this.productCatalogueService.updateProductCatalogue(productCatalogueDTO, id);
        return new ResponseEntity<>(updatedProductCatalogueDTO, HttpStatus.OK);

    }

    // Admin will DELETE the product
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProductCatalogue(@PathVariable Long id){
        this.productCatalogueService.deleteProductCatalogue(id);
        return ResponseEntity.ok(new ApiResponse("Product Deleted Successfully",true));
    }

    //  Admin can see all the products listed on website
    @GetMapping("/all")
    public ResponseEntity<List<ProductCatalogueDTO>> getAllProducts(){
        return ResponseEntity.ok(this.productCatalogueService.getAllProductCatalogue());
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductCatalogueDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(this.productCatalogueService.getProductCatalogueById(id));
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<ProductCatalogueDTO>> getProductsByCategory(@PathVariable String category ){
        return ResponseEntity.ok(this.productCatalogueService.getProductByCategory(category.toLowerCase()));
    }

    @GetMapping("/search")
    public List<ProductCatalogue> searchProducts(@RequestParam String keyword) {
        return productCatalogueService.searchProducts(keyword);
    }
}
