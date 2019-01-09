package ar.com.taskmanager.model.domain;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by ArielRamirez on 3/1/2019.
 */

@Entity
@Table(name = "Task", schema = "springtest")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "taskName")
    private String taskName;
    @Column(name = "taskType")
    private String taskType;
    @Column(name = "taskDescription")
    private String taskDescription;
    @ManyToMany
    private Set<User> taskOwners;

    public Set<User> getTaskOwners() {
        return taskOwners;
    }

    public void setTaskOwners(Set<User> taskOwners) {
        this.taskOwners = taskOwners;
    }

    public Task() {
    }

    public Task(Long Id, String taskName, String taskType) {
        this.id = id;
        this.taskName = taskName;
        this.taskType = taskType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Set<User> getOwners() {
        return taskOwners;
    }

    public void setOwners(Set<User> taskOwners) {
        this.taskOwners = taskOwners;
    }
}
