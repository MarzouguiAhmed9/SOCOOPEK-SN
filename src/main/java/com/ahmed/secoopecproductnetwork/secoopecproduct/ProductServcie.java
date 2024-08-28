package com.ahmed.secoopecproductnetwork.secoopecproduct;

import com.ahmed.secoopecproductnetwork.common.PageResponse;
import com.ahmed.secoopecproductnetwork.exception.OperationNotPermit;
import com.ahmed.secoopecproductnetwork.file.FileStorageSerivce;
import com.ahmed.secoopecproductnetwork.history.ProductHistory;
import com.ahmed.secoopecproductnetwork.history.ProductTransactionHistory;
import com.ahmed.secoopecproductnetwork.user.User;
import jakarta.mail.Multipart;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServcie {
    private final SecoopecProductRepository secoopecProductRepository;
    private final ProductMapper productMapper;
    private  final ProductTransactionHistory productTransactionHistory;
    private  final FileStorageSerivce fileStorageService;
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

    public PageResponse<ProdcutResponse> findallbookbyowner(int page, int size, Authentication authentication) {
        User user=(User)authentication.getPrincipal();
        Pageable pageable=PageRequest.of(page,size);
        Page <SecoopecProduct> products=secoopecProductRepository.findalldisplayproductsbyowner(pageable,user.getId());
        List <ProdcutResponse> prodcutsresponses=products.stream().map(productMapper::toproductresponse).toList();
        return  new PageResponse<>(prodcutsresponses,products.getNumber(),products.getSize(),products.getTotalElements(),products.getTotalPages(),products.isFirst(),products.isLast());
    }

   public PageResponse<BorrowedResponse> findallborrwedproduct(int page, int size, Authentication authentication) {
       User user=(User)authentication.getPrincipal();
       Pageable pageable=PageRequest.of(page,size);
       Page <ProductHistory> products=productTransactionHistory.findAllproducts(pageable,user.getId());
       List <BorrowedResponse> prodcutResponses=products.stream().map( productMapper::toBorrowedResponse).toList();

       return  new PageResponse<>(prodcutResponses,products.getNumber(),products.getSize(),products.getTotalElements(),products.getTotalPages(),products.isFirst(),products.isLast());
   }

    public PageResponse<BorrowedResponse> findallreturnedproduct(int page, int size, Authentication authentication) {
        User user=(User)authentication.getPrincipal();
        Pageable pageable=PageRequest.of(page,size);
        Page <ProductHistory> products=productTransactionHistory.findAllreturnedproducts(pageable,user.getId());
        List <BorrowedResponse> prodcutResponses=products.stream().map( productMapper::toBorrowedResponse).toList();

        return  new PageResponse<>(prodcutResponses,products.getNumber(),products.getSize(),products.getTotalElements(),products.getTotalPages(),products.isFirst(),products.isLast());
    }

    public Integer updateShareableStatus(Integer id, Authentication connectedUser) {
        // Fetch the SecoopecProduct entity by its ID
        SecoopecProduct secoopecProduct = secoopecProductRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product found with this ID"));

        // Get the currently authenticated user
        User user = (User) connectedUser.getPrincipal();

        // Check if the authenticated user is the owner of the product
        if (!user.getId().equals(secoopecProduct.getOwner().getId())) {
            throw new OperationNotPermit("You are not authorized to update this product");
        }
        secoopecProduct.setShareable(!secoopecProduct.isShareable());
        secoopecProductRepository.save(secoopecProduct);
        return  id;


    }

    public Integer updateArchivedStatus(Integer id, Authentication connecteduser) {
        // Fetch the SecoopecProduct entity by its ID
        SecoopecProduct secoopecProduct = secoopecProductRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product found with this ID"));

        // Get the currently authenticated user
        User user = (User) connecteduser.getPrincipal();

        // Check if the authenticated user is the owner of the product
        if (!user.getId().equals(secoopecProduct.getOwner().getId())) {
            throw new OperationNotPermit("You are not authorized to update this product");
        }
        secoopecProduct.setArchived(!secoopecProduct.isArchived());
        secoopecProductRepository.save(secoopecProduct);
        return  id;
    }

    public Integer borrowproduct(Integer id, Authentication connecteduser) {
        SecoopecProduct secoopecProduct=secoopecProductRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("no book found"));
        if(secoopecProduct.isArchived() || !secoopecProduct.isShareable()){
            throw  new OperationNotPermit("the request cannot be borrowed");

        }
        User user = (User) connecteduser.getPrincipal();

        if(Objects.equals(secoopecProduct.getOwner().getId(),user.getId())){
            throw new OperationNotPermit("you cannot barrow your product");
        }
        final boolean isalreadyborrowed=productTransactionHistory.isalreadyborrowedbyuser(id,user.getId());
        if(isalreadyborrowed){
            throw new OperationNotPermit("already borrowed");
        }
        ProductHistory productHistory=ProductHistory.builder().user(user).product(secoopecProduct).returned(false).returnapprouved(false).build();
        return  productTransactionHistory.save(productHistory).getId();
    }

    public Integer returnbarrowproduct(Integer id, Authentication connecteduser) {
        SecoopecProduct secoopecProduct=secoopecProductRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("no book found"));
        if(secoopecProduct.isArchived() || !secoopecProduct.isShareable()){
            throw  new OperationNotPermit("the request cannot be borrowed");

        }
        User user = (User) connecteduser.getPrincipal();

        if(Objects.equals(secoopecProduct.getOwner().getId(),user.getId())){
            throw new OperationNotPermit("you cannot barrow or return your product");
        }
        ProductHistory productHistory=productTransactionHistory.findByproductidanduserid(id,user.getId())
                .orElseThrow(()->new OperationNotPermit("you did not borrow this product"));

     productHistory.setReturned(true);
        return  productTransactionHistory.save(productHistory).getId();

    }

    public Integer approubeproductreturned(Integer id, Authentication connecteduser) {
        SecoopecProduct secoopecProduct=secoopecProductRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("no book found"));
        if(secoopecProduct.isArchived() || !secoopecProduct.isShareable()){
            throw  new OperationNotPermit("the request cannot be borrowed");

        }
        User user = (User) connecteduser.getPrincipal();

        if(Objects.equals(secoopecProduct.getOwner().getId(),user.getId())){
            throw new OperationNotPermit("you cannot barrow or return your product");
        }
        ProductHistory productHistory=productTransactionHistory.findByproductidandownerid(id,user.getId())
                .orElseThrow(()->new OperationNotPermit("not returned yet"));
        productHistory.setReturnapprouved(true);
        return  productTransactionHistory.save(productHistory).getId();


    }

    public void uploadimageproduct(MultipartFile file, Authentication connecteduser, Integer id) {
        SecoopecProduct secoopecProduct = secoopecProductRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product found with id: " + id));

        User user = (User) connecteduser.getPrincipal();
        String productCover = fileStorageService.saveFile(file, user.getId());

        if (productCover == null || productCover.isEmpty()) {
            throw new RuntimeException("File storage service returned null or empty path");
        }

        secoopecProduct.setProductimage(productCover);

        // Debug output before saving
        System.out.println("Setting product image to: " + productCover);
        System.out.println("Product before save: " + secoopecProduct);

        secoopecProduct = secoopecProductRepository.save(secoopecProduct);

        // Debug output after saving
        System.out.println("Product after save: " + secoopecProduct);
    }

}
