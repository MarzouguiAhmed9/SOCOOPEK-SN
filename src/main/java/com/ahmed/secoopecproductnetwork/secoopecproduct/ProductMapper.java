package com.ahmed.secoopecproductnetwork.secoopecproduct;

import com.ahmed.secoopecproductnetwork.file.FileUtils;
import com.ahmed.secoopecproductnetwork.history.ProductHistory;
import org.aspectj.util.FileUtil;
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
                .archived(secoopecProduct.isArchived()).shareable(secoopecProduct.isShareable()).owner(secoopecProduct.getOwner().getFirstname()).rate(secoopecProduct.getrate()).productimage(FileUtils.readFileLocation(secoopecProduct.getProductimage())).build();
    }

    public BorrowedResponse toBorrowedResponse(ProductHistory productHistory) {
        return BorrowedResponse.builder().id(productHistory.getId()).producttitle(productHistory.getProduct().getProducttitle()).price(productHistory.getProduct().getPrice())
                .description(productHistory.getProduct().getDescription()).rate(productHistory.getProduct().getrate()).returned(productHistory.isReturned()).returnedaproved(productHistory.isReturnapprouved()).build();
    }

}
