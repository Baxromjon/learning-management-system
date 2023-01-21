package com.example.learningmanagementsystem.entity;

import com.example.learningmanagementsystem.entity.template.AbsEntity;
import com.example.learningmanagementsystem.enums.OpenLessonEnumStatus;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = EntityName.OPEN_LESSON)
@SQLDelete(sql = "UPDATE " + EntityName.OPEN_LESSON + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class OpenLesson extends AbsEntity {

    @Column(name = ColumnName.TITLE)
    private String title;

    @Column(name = ColumnName.ACTIVE)
    private boolean active;

    @Column(name = ColumnName.DESCRIPTION)
    private String description;

    @Column(name = ColumnName.SPEAKER)
    private String speakerFullName;

    @Column(name = ColumnName.START_TIME)
    private Timestamp startTime;

    @Column(name = ColumnName.URL)
    private String url;

    @Enumerated(EnumType.STRING)
    private OpenLessonEnumStatus status;
}
