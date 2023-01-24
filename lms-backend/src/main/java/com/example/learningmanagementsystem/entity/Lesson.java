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
@Entity(name = EntityName.LESSON)
@SQLDelete(sql = "UPDATE " + EntityName.LESSON + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class Lesson extends AbsEntity {

    @Column(name = ColumnName.TITLE)
    private String title;

    @Column(name = ColumnName.LESSON_NUMBER)
    private int lessonNumber;

    @ManyToOne
    private Group group;

    @Column(name = ColumnName.URL)
    private String url;

    @ManyToOne
    private Attachment video;
}
