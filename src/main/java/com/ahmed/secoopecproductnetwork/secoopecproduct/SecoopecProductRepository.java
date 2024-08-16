package com.ahmed.secoopecproductnetwork.secoopecproduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SecoopecProductRepository extends JpaRepository<SecoopecProduct,Integer> {
    @Query("""
        SELECT sp 
        FROM SecoopecProduct sp 
        WHERE sp.archived = false 
        AND sp.shareable = true 
        AND sp.owner.id != :userid
    """)
    Page<SecoopecProduct> findalldisplayproducts(Pageable pageable, Integer userid);

    @Query("""
        SELECT sp 
        FROM SecoopecProduct sp 
        WHERE sp.archived = false 
        AND sp.shareable = true 
        AND sp.owner.id = :userid
    """)
    Page<SecoopecProduct> findalldisplayproductsbyowner(Pageable pageable, Integer userid);
}
