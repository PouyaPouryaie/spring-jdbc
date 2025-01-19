package ir.bigz.springboot.springjdbc.user;


import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class User
{

    @Id
    private Integer id;
    private String name;
    private String email;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private int version; // Version field for optimistic locking


    public User()
    {
    }

    public User(Integer id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
