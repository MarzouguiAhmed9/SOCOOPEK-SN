package com.ahmed.secoopecproductnetwork.feedback;

import com.ahmed.secoopecproductnetwork.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name="FeedBack")
public class FeedBackController {
    private final FeedBackService service;


@PostMapping
    public ResponseEntity<Integer> savefeedback(@Valid @RequestBody FeedBackRequest feedBackRequest, Authentication connecteduser)
    {
        return ResponseEntity.ok(service.save(feedBackRequest,connecteduser));
    }

 @GetMapping("/product/{product-id}")
 public ResponseEntity<PageResponse<Feedbackresponse>> findallfeedbackbyproductid(
         @PathVariable("product-id")Integer productid,
        @RequestParam(name = "size", defaultValue = "10",required = false)   int size,
         @RequestParam ( name = "page", defaultValue = "10" ,required = false) int page,
         Authentication connecteduser
 ){
    return ResponseEntity.ok(service.findallfeedbackbyproductid(productid,page,size,connecteduser));
 }



}
