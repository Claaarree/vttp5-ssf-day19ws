package sg.edu.nus.iss.vttp5a_ssf_day19ws.model;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Todo {
    // @NotBlank(message = "Please input an appropriate id!")
    // @Size(min = 0, max = 50, message = "Id must be less than 50 characters!")
    // private String id;
    private String id;

    @NotBlank(message = "Please input an appropriate name!")
    @Size(min = 10, max = 50, message = "Name must be between 10 to 50 characters!")
    private String name;

    @NotBlank(message = "Please input an appropriate description!")
    @Size(min = 0, max = 255, message = "Description must be less than 255 characters!")
    private String description;

    @NotNull(message = "Please select a due date!")
    @FutureOrPresent(message = "Please select a day from today!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    @NotEmpty(message = "Please select an appropriate priority level!")
    private String priorityLevel;

    @NotEmpty(message = "Please select an appropriate status level!")
    private String status;

    @NotNull(message = "Please select a created date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private Date createAt;

    @NotNull(message = "Please select an update date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private Date updatedAt;

   
    // public Todo(String id) {
    //     this.id = id;
    // }

    public Todo() {
        // this.id = UUID.randomUUID().toString();
    }

    public Todo(String id, String name, String description, Date dueDate, String priorityLevel, String status,
            Date createAt, Date updatedAt) {
        this.id = Optional.of(id).orElse(UUID.randomUUID().toString());
        // this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priorityLevel = priorityLevel;
        this.status = status;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
