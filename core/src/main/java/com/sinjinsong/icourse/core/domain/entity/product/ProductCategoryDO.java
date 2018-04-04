package com.sinjinsong.icourse.core.domain.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDO implements Serializable{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_category.id
     *
     * @mbggenerated
     */
    @NotNull
    private Long id;

    @NotNull
    private String name;
    private List<ProductDO> products;
    private String description;
    @JsonIgnore
    private Boolean isOnBoard;
    private String imageUrl;
    
    public ProductCategoryDO(String name){
        this.name = name;
    }
}