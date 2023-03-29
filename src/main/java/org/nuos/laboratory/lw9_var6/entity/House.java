package org.nuos.laboratory.lw9_var6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class House {
int id;
int numberOfApartment;
double square;
int floor;
int numberOfRooms;
String street;
}
