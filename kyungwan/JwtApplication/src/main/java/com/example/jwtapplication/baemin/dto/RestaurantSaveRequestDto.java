package com.example.jwtapplication.baemin.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantSaveRequestDto {
    String name;
    String master;
}
