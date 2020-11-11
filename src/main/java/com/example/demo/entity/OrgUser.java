package com.example.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * org_user
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgUser implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}