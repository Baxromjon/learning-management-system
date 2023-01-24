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
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = EntityName.USERS_GROUPS)
@SQLDelete(sql = "UPDATE " + EntityName.USERS_GROUPS + " SET deleted = TRUE WHERE id=?")
@Where(clause = "deleted=false")
public class UserGroups extends AbsEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Group group;
}
