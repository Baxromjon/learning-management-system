package com.example.learningmanagementsystem.entity;

import com.example.learningmanagementsystem.entity.template.AbsEntity;
import com.example.learningmanagementsystem.utils.ColumnName;
import com.example.learningmanagementsystem.utils.EntityName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = EntityName.PAYMENT)
@SQLDelete(sql = "UPDATE " + EntityName.PAYMENT + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class Payment extends AbsEntity {

    @Column(name = ColumnName.AMOUNT)
    private double amount;

    @ManyToOne
    private User user;

    @Column(name = ColumnName.DATE)
    private Date date;

    @ManyToOne
    private Cash cash;
}
