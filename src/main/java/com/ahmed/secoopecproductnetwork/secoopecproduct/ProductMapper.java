package com.ahmed.secoopecproductnetwork.secoopecproduct;

import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public SecoopecProduct toproduct(Productrequest request){
        return SecoopecProduct.builder().id(request.id()).
                producttitle(request.producttitle()).
                price(request.price()).
                identifiant(request.identifiant()).
                description(request.description()).
                archived(false).
                shareable(request.shareable()).build();
    }

    public ProdcutResponse toproductresponse(SecoopecProduct secoopecProduct) {
        return ProdcutResponse.builder().id(secoopecProduct.getId()).producttitle(secoopecProduct.getProducttitle()).
                price(secoopecProduct.getPrice()).identifiant(secoopecProduct.getIdentifiant()).description(secoopecProduct.getDescription())
                .archived(secoopecProduct.isArchived()).shareable(secoopecProduct.isShareable()).owner(secoopecProduct.getOwner().getFirstname()).rate(secoopecProduct.getrate()).build();
    }
}
