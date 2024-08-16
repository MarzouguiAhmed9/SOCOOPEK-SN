package com.ahmed.secoopecproductnetwork.secoopecproduct;

import com.ahmed.secoopecproductnetwork.common.BaseEntite;
import com.ahmed.secoopecproductnetwork.feedback.Feedback;
import com.ahmed.secoopecproductnetwork.history.ProductHistory;
import com.ahmed.secoopecproductnetwork.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.PostMapping;

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
    private String productimage;
    private double rate;
    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name="ownerid")
    private User owner;
   @OneToMany(mappedBy = "secoopecProduct")
    private List<Feedback> feedbacks;

   @OneToMany(mappedBy ="product" )
    private List <ProductHistory> histories;

   @Transient
    public double getrate(){
       if (feedbacks==null || feedbacks.isEmpty()){return 0.0;}
       var rate=this.feedbacks.stream().mapToDouble(Feedback::getNote).average().orElse(0.0);
       double rountedrate=Math.round(rate*10.0)/10.0;
       return rountedrate;
   }



}
