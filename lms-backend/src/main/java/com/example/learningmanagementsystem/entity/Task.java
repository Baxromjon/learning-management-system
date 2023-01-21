package com.example.learningmanagementsystem.entity;

import com.example.learningmanagementsystem.entity.template.AbsEntity;
import com.example.learningmanagementsystem.enums.EnumPriority;
import com.example.learningmanagementsystem.utils.ColumnName;
import com.example.learningmanagementsystem.utils.EntityName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = EntityName.TASK)
@SQLDelete(sql = "UPDATE " + EntityName.TASK + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class Task extends AbsEntity {

    @Column(name = ColumnName.TITLE)
    private String title;

    @Column(name = ColumnName.TASK_NUMBER)
    private int taskNumber;

    @ManyToOne
    private Lesson lesson;

    @Column(name = ColumnName.URL)
    private String url;

    @Enumerated(EnumType.STRING)
    private EnumPriority priority;

    @Column(name = ColumnName.MUST_COMPLETE)
    private boolean mustComplete;

    @Column(name = ColumnName.DESCRIPTION)
    private String description;

}
