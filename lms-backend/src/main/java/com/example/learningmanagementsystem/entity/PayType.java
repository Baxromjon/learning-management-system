package com.example.learningmanagementsystem.entity;

import com.example.learningmanagementsystem.entity.template.AbsEntity;
import com.example.learningmanagementsystem.utils.EntityName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = EntityName.PAY_TYPE)
@SQLDelete(sql = "UPDATE " + EntityName.PAY_TYPE + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class PayType extends AbsEntity {
    private String name;
    private String description;
}
