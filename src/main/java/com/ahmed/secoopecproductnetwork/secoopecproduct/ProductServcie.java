package com.ahmed.secoopecproductnetwork.secoopecproduct;

import com.ahmed.secoopecproductnetwork.common.PageResponse;
import com.ahmed.secoopecproductnetwork.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServcie {
    private final SecoopecProductRepository secoopecProductRepository;
    private final ProductMapper productMapper;
    public Integer save(Productrequest prodcutrequest, Authentication connecteduser){
       User user= (User)connecteduser.getPrincipal();
       SecoopecProduct secoopecProduct=productMapper.toproduct(prodcutrequest);
       secoopecProduct.setOwner(user);
       return secoopecProductRepository.save(secoopecProduct).getId();
    }


    public ProdcutResponse findById(Integer productid) {
        return (ProdcutResponse) secoopecProductRepository.findById(productid).
                map(productMapper::toproductresponse).
                orElseThrow(()->new EntityNotFoundException("no book found"));
    }


    public PageResponse<ProdcutResponse> findallbooks(int page, int size, Authentication connecteduser) {
        User user = ((User)connecteduser.getPrincipal());
        //parametre
        Pageable pageable= PageRequest.of(page,size, Sort.by("createdDate"));
        Page<SecoopecProduct> products=secoopecProductRepository.findalldisplayproducts(pageable,user.getId());
        List<ProdcutResponse> productsresponse=products.stream().map(productMapper::toproductresponse).toList();
        return  new PageResponse<>(productsresponse,products.getNumber(),products.getSize(),products.getTotalElements(),products.getTotalPages(),products.isFirst(),products.isLast());
    }
}
