package com.example.learningmanagementsystem.entity;

import com.example.learningmanagementsystem.entity.template.AbsEntity;
import com.example.learningmanagementsystem.enums.TaskStatusEnum;
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
@Entity(name = EntityName.USER_TASK_ANSWER)
@SQLDelete(sql = "UPDATE " + EntityName.USER_TASK_ANSWER + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class UserTaskAnswer extends AbsEntity {

    @Column(name = ColumnName.ANSWER)
    private String answer;

    @Column(name = ColumnName.RATING)
    private double rating;

    @Enumerated(EnumType.STRING)
    private TaskStatusEnum statusEnum;

    @ManyToOne
    private Attachment attachment;

    @ManyToOne
    private UserTaskAnswer userTaskAnswer;
}
