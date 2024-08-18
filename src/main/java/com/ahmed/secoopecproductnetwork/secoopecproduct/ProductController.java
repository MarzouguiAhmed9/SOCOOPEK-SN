package com.ahmed.secoopecproductnetwork.secoopecproduct;

import com.ahmed.secoopecproductnetwork.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.Multipart;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Tag(name="product")
public class ProductController {

    private final ProductServcie service;

    @PostMapping
    public ResponseEntity<Integer> savebook (@Valid @RequestBody Productrequest productrequest, Authentication connecteduser)
    {
        return ResponseEntity.ok(service.save(productrequest,connecteduser));
    }


    @GetMapping("{book-id}")
    public ResponseEntity<ProdcutResponse>findbookbyid
            (
                    @PathVariable("book-id")Integer productid
            ){
        return ResponseEntity.ok(service.findById(productid));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProdcutResponse>> finadallproduct (
            @RequestParam(name="page",defaultValue = "0",required = false)int page,
            @RequestParam(name="size",defaultValue = "10",required = false)int size ,
            Authentication connecteduser){
        return ResponseEntity.ok(service.findallbooks(page,size,connecteduser));

    }

    @GetMapping("/owner")
        public ResponseEntity<PageResponse<ProdcutResponse>>findallproductbyowner(
                @RequestParam(name="page",defaultValue = "0",required = false) int page,
                @RequestParam(name="page",defaultValue = "0",required = false) int size,
                Authentication authentication){

               return ResponseEntity.ok(service.findallbookbyowner(page,size,authentication));
        }
    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedResponse>>Findallborrwedproduct(
            @RequestParam(name="page",defaultValue = "0",required = false) int page,
            @RequestParam(name="page",defaultValue = "0",required = false) int size,
            Authentication authentication){

        return ResponseEntity.ok(service.findallborrwedproduct(page,size,authentication));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedResponse>>Findallreterneddproduct(
            @RequestParam(name="page",defaultValue = "0",required = false) int page,
            @RequestParam(name="page",defaultValue = "0",required = false) int size,
            Authentication authentication){

        return ResponseEntity.ok(service.findallreturnedproduct(page,size,authentication));
    }

    @PatchMapping("/shareable/{product-id}")
    public ResponseEntity<Integer >updateshareavlestatus(@PathVariable("product-id")Integer id,Authentication connecteduser)
    {
        return  ResponseEntity.ok(service.updateShareableStatus(id,connecteduser));
    }

    @PatchMapping("/archived/{product-id}")
    public ResponseEntity<Integer >updatearchivedstatus(@PathVariable("product-id")Integer id,Authentication connecteduser)
    {
        return  ResponseEntity.ok(service.updateArchivedStatus(id,connecteduser));
    }

    @PostMapping("/borrowproduct/{product-id}")
    public ResponseEntity<Integer >borrowproduct(@PathVariable("product-id")Integer id,Authentication connecteduser)
    {
        return  ResponseEntity.ok(service.borrowproduct(id,connecteduser));
    }

    @PatchMapping ("/borrow/return/{product-id}")
    public ResponseEntity<Integer >returnborrowproduct(@PathVariable("product-id")Integer id,Authentication connecteduser)
    {
        return  ResponseEntity.ok(service.returnbarrowproduct(id,connecteduser));
    }

    @PatchMapping("/borrow/approve/{product-id}")
    public ResponseEntity<Integer> approuvereturnedproduct(@PathVariable("product-id")Integer id,Authentication conecteduser){
        return ResponseEntity.ok(service.approubeproductreturned(id,conecteduser));
    }

    @PostMapping(value = "/cover/{product-id}",consumes = "multipart/form-data")
    public ResponseEntity<?> uploadproductimage(
            @PathVariable("product-id")Integer id,
            @Parameter()
            @RequestPart ("file") MultipartFile file,
            Authentication connecteduser

            )
    {
        service.uploadimageproduct(file,connecteduser,id);
        return ResponseEntity.accepted().build();
    }


}
