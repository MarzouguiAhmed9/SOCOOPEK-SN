package com.ahmed.secoopecproductnetwork.secoopecproduct;

import com.ahmed.secoopecproductnetwork.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import javafx.concurrent.Service;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping   public ResponseEntity<PageResponse<ProdcutResponse>> finadallproduct (
            @RequestParam(name="page",defaultValue = "0",required = false)int page,
            @RequestParam(name="size",defaultValue = "10",required = false)int size ,
            Authentication connecteduser){
        return ResponseEntity.ok(service.findallbooks(page,size,connecteduser));

    }

}
