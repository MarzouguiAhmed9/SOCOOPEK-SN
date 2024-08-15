package com.ahmed.secoopecproductnetwork.secoopecproduct;

import com.ahmed.secoopecproductnetwork.common.BaseEntite;
import com.ahmed.secoopecproductnetwork.feedback.Feedback;
import com.ahmed.secoopecproductnetwork.history.ProductHistory;
import com.ahmed.secoopecproductnetwork.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SecoopecProduct extends BaseEntite {


    private String producttitle;
    private float price;
    private int identifiant;
    private String description;
    private String useropinion;
    private String productimage;
    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name="ownerid")
    private User owner;
   @OneToMany(mappedBy = "secoopecProduct")
    private List<Feedback> feedbacks;

   @OneToMany(mappedBy ="product" )
    private List <ProductHistory> histories;

}
