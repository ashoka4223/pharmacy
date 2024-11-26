package com.sinfolix.Sai_Samartha.DTO;
import com.sinfolix.Sai_Samartha.Entities.Order;
import com.sinfolix.Sai_Samartha.Entities.ProductCatalogue;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
public class OrderProductDTO {

    private OrderDTO orderDTO;
    private ProductCatalogueDTO productCatalogueDTO;
    private int quantity;

}
