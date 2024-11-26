
package com.sinfolix.Sai_Samartha.Repository;

import com.sinfolix.Sai_Samartha.Entities.ProductCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCatalogueRepo extends JpaRepository<ProductCatalogue,Long> {


    @Query("SELECT p FROM ProductCatalogue p WHERE p.categories = :categories")
    List<ProductCatalogue> findByCategory(@Param("categories") String categories);

    List<ProductCatalogue> findByMedicineNameContainingIgnoreCaseOrCompanyNameContainingIgnoreCase(String keyword, String keyword1);
}
