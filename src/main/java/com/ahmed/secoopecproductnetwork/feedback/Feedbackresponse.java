package com.ahmed.secoopecproductnetwork.feedback;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feedbackresponse {
    private Double note;
    private String comment;

    private boolean ownFeedback;
}
