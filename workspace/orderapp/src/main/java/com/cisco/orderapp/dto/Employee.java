package com.cisco.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    int id;
    String title;
    Map<String, String> personal = new HashMap<>();
    List<String> programmingSkills = new ArrayList<>();
}
