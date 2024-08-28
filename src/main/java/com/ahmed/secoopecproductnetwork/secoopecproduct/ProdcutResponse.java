package com.ahmed.secoopecproductnetwork.secoopecproduct;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdcutResponse {
    public double getRate() {
        return rate;
    }

    private Integer id;
    private String producttitle;
    private float price;
    private int identifiant;
    private String description;
    private String owner;
    private byte[] productimage;
    private double rate;
    private boolean archived;
    private boolean shareable;
}
