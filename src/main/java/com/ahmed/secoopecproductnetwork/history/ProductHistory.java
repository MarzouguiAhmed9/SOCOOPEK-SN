package com.ahmed.secoopecproductnetwork.history;

import com.ahmed.secoopecproductnetwork.common.BaseEntite;
import com.ahmed.secoopecproductnetwork.secoopecproduct.SecoopecProduct;
import com.ahmed.secoopecproductnetwork.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProductHistory extends BaseEntite {

    @ManyToOne
    @JoinColumn(name="userid")
    private User user;

    @ManyToOne
    @JoinColumn(name="productid")
    private SecoopecProduct product;


private  boolean returned;

private boolean returnapprouved;



}
