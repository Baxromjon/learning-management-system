package com.example.learningmanagementsystem.entity;

import com.example.learningmanagementsystem.entity.template.AbsEntity;
import com.example.learningmanagementsystem.utils.ColumnName;
import com.example.learningmanagementsystem.utils.EntityName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = EntityName.GROUP)
@SQLDelete(sql = "UPDATE " + EntityName.GROUP + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class Group extends AbsEntity {

    @Column(name = ColumnName.TITLE)
    private String title;

    @Column(name = ColumnName.DESCRIPTION)
    private String description;

    @Column(name = ColumnName.INVITE_LINK)
    private String inviteLink;

    @Column(name = ColumnName.TIME)
    private Timestamp time;

//    @Column(name = ColumnName.WEEK_DAYS, columnDefinition = "integer[]")
//    @Type(type = "ai.ecma.pdp.online.type.GenericIntArrayType")
//    private Integer[] weekDays;

    @ManyToOne
    private Course course;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> mentors;
}
