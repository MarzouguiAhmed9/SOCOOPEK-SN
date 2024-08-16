package com.ahmed.secoopecproductnetwork.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductTransactionHistory extends JpaRepository<ProductHistory,Integer> {


    @Query("""

Select history\s
FROM ProductHistory history
WHERE history.user.id=:id


""")



    Page<ProductHistory> findAllproducts(Pageable pageable, Integer id);

    @Query("""

Select history\s
FROM ProductHistory history
WHERE history.product.owner.id=:id


""")


    Page<ProductHistory> findAllreturnedproducts(Pageable pageable, Integer id);
@Query("""
         SELECT 
         (COUNT(*) > 0 ) as isBorrowed
         FROM ProductHistory history
         WHERE history.user.id=:userid
         AND history.product.id=:id
         AND history.returnapprouved=false

""")
    boolean isalreadyborrowedbyuser(Integer id, Integer userid);
@Query
        ("""
SELECT transaction
FROM ProductHistory transaction
WHERE transaction.user.id=:userid
AND transaction.product.id=:pid
AND transaction.returned=false
AND transaction.returnapprouved=false


""")
Optional<ProductHistory> findByproductidanduserid(Integer pid, Integer userid);
}
