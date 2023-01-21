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
@Entity(name = EntityName.OPEN_LESSON_STUDENTS)
@SQLDelete(sql = "UPDATE " + EntityName.OPEN_LESSON_STUDENTS + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class OpenLessonStudents extends AbsEntity {

    @Column(name = ColumnName.FULL_NAME)
    private String fullName;

    @Column(name = ColumnName.PHONE_NUMBER)
    private String phoneNumber;

    @ManyToOne
    private OpenLesson openLesson;

    @Column(name = ColumnName.COMMENT)
    private String comment;


}
