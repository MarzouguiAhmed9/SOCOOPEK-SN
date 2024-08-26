package com.ahmed.secoopecproductnetwork.secoopecproduct;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

public record Productrequest(

        Integer id,
        @NotNull(message= "100")
        @NotEmpty (message= "100")
         String producttitle,

        @NotNull(message= "100")
         float price,

        @NotNull(message= "100")

        int identifiant,
        @NotNull(message= "100")
        @NotEmpty (message= "100")
       String description,
         boolean shareable ) {
}
