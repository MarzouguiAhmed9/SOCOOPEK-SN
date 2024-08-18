package com.ahmed.secoopecproductnetwork.feedback;

import com.ahmed.secoopecproductnetwork.secoopecproduct.SecoopecProduct;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeebackMapper {

    public Feedback tofeedback(FeedBackRequest feedBackRequest) {
        return Feedback.builder().note(feedBackRequest.note).comment(feedBackRequest.comment).secoopecProduct(SecoopecProduct.builder().id(feedBackRequest.bookid).archived(false).shareable(false).build()).build();
    }

    public Feedbackresponse tofeedbackresponse (Feedback feedback,Integer id){
        return Feedbackresponse.builder().note(feedback.getNote()).comment(feedback.getComment()).ownFeedback(Objects.equals(feedback.getCreatedBy(),id)).build();
    }
}
