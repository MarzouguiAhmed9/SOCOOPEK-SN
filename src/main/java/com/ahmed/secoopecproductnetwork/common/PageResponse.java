package com.ahmed.secoopecproductnetwork.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PageResponse<T> {

    private List<T> content;
    private int number;
    private int size;
    private long totalelements;
    private int totalpages;
    private boolean first;
    private boolean Last;


}
