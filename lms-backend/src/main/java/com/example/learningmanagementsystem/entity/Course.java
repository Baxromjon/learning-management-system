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

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = EntityName.COURSE)
@SQLDelete(sql = "UPDATE " + EntityName.COURSE + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class Course extends AbsEntity {

    @Column(name = ColumnName.NAME)
    private String name;

    @Column(name = ColumnName.DESCRIPTION)
    private String description;

    @ManyToOne
    private User mentorId;

    @Column(name = ColumnName.ACTIVE)
    boolean active;

    @Column(name = ColumnName.LEVEL)
    private String level;
}
