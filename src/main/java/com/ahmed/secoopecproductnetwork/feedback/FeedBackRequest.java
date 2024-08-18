package com.ahmed.secoopecproductnetwork.feedback;

import jakarta.validation.constraints.*;

public class FeedBackRequest {
    @Positive(message = "200")
    @Min(message = "201" , value = 0)
    @Max(message = "202" , value = 5)

Double note;
@NotNull(message = "203")
@NotEmpty(message = "203")
@NotBlank(message = "203")
    String comment;

    @NotNull(message = "204")

    Integer bookid;


}
