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
@Entity(name = EntityName.USER_TASK)
@SQLDelete(sql = "UPDATE " + EntityName.USER_TASK + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class UserTask extends AbsEntity {

    @Column(name = ColumnName.IS_LOOKED)
    private boolean looked;

    @Column(name = ColumnName.PERCENT)
    private int percent;

    @Enumerated(EnumType.STRING)
    private TaskStatusEnum taskStatus;

    @Column(name = ColumnName.TEXT)
    private String text;

    @ManyToOne
    private Attachment attachment;

    @ManyToOne
    private Task task;


}
