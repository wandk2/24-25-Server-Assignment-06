package com.example.jwtapplication.baemin.domain;

import com.example.jwtapplication.baemin.dto.RestaurantInfoDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED )
@Getter

public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RESTAURANT_NAME", nullable = false)
    private String name;

    @Column(name = "RESTAURANT_MASTER", nullable = false)
    private String master;

    @Builder
    public Restaurant(String name, String master) {
        this.name = name;
        this.master = master;
    }
    public void update (RestaurantInfoDto restaurantInfoDto) {
        this.name = restaurantInfoDto.getName();
        this.master = restaurantInfoDto.getMaster();
    }
}
