package com.ahmed.secoopecproductnetwork.feedback;

import com.ahmed.secoopecproductnetwork.common.PageResponse;
import com.ahmed.secoopecproductnetwork.exception.OperationNotPermit;
import com.ahmed.secoopecproductnetwork.secoopecproduct.SecoopecProduct;
import com.ahmed.secoopecproductnetwork.secoopecproduct.SecoopecProductRepository;
import com.ahmed.secoopecproductnetwork.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedBackService {
    private final SecoopecProductRepository secoopecProductRepository;
    private final FeebackMapper feebackMapper;
    private final FeedBackrepository feedBackrepository;

    public Integer save(FeedBackRequest feedBackRequest, Authentication connecteduser) {
        User user=(User) (connecteduser.getPrincipal());
        SecoopecProduct secoopecProduct=secoopecProductRepository.findById(feedBackRequest.bookid).orElseThrow(()->new EntityNotFoundException("no product found"));
        if(secoopecProduct.isArchived() || !secoopecProduct.isShareable()){
            throw  new OperationNotPermit("you cannot give a feedback for archived product");

        }

        if(Objects.equals(secoopecProduct.getOwner().getId(),user.getId())){
            throw new OperationNotPermit("you cannot gibe feedback your product");
        }
        Feedback feedback=feebackMapper.tofeedback(feedBackRequest);
      return  feedBackrepository.save(feedback).getId();

    }

    public PageResponse<Feedbackresponse> findallfeedbackbyproductid(Integer productid, int page, int size,  Authentication connecteduser) {
        User user=(User)connecteduser.getPrincipal();
        Pageable pageable= PageRequest.of(page,size);
        Page<Feedback> feedbacks=feedBackrepository.findallfeedbackbyproductid(productid,pageable);
        List<Feedbackresponse> feeedbackre=feedbacks.stream().map(r->feebackMapper.tofeedbackresponse(r,user.getId())).toList();
        return new PageResponse<>(
                feeedbackre,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );    }
}
