package com.ahmed.secoopecproductnetwork.secoopecproduct;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedResponse {
    private Integer id;
    private String producttitle;
    private float price;
    private String description;
    private double rate;
    private boolean returned;
    private boolean returnedaproved;

}
