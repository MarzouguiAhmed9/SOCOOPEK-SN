package com.ahmed.secoopecproductnetwork.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedBackrepository extends JpaRepository<Feedback,Integer> {
@Query("""

SELECT feedback
FROM Feedback feedback
WHERE feedback.secoopecProduct.id=:productid


""")
    Page<Feedback> findallfeedbackbyproductid(Integer productid, Pageable pageable);
}
