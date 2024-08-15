package com.ahmed.secoopecproductnetwork.feedback;

import com.ahmed.secoopecproductnetwork.common.BaseEntite;
import com.ahmed.secoopecproductnetwork.secoopecproduct.SecoopecProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.sound.sampled.AudioFileFormat;
import java.awt.print.Book;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class Feedback extends BaseEntite {

    private Double note;

    private String comment;

   @ManyToOne
    @JoinColumn(name ="productid" )
    private SecoopecProduct secoopecProduct;



}
