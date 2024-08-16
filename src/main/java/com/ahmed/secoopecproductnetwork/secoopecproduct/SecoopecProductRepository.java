package com.ahmed.secoopecproductnetwork.secoopecproduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SecoopecProductRepository extends JpaRepository<SecoopecProduct,Integer> {
    @Query("""
SELECT SecoopecProduct \s
FROM SecoopecProduct secoopecproduct
WHERE secoopecproduct.archived=false
AND secoopecproduct.shareable=true\s
AND SecoopecProduct .owner.id != :userid

""")
    Page<SecoopecProduct> findalldisplayproducts(Pageable pageable, Integer userid);

}
