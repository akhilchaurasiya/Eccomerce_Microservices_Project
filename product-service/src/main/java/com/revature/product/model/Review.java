package com.revature.product.model;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "Reviews")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_id", nullable = false)
    private Product product;

    @Column(name = "User_id", nullable = false)
    private String userId;

    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "ReviewText")
    private String reviewText;
}