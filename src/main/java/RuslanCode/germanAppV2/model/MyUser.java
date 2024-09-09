package RuslanCode.germanAppV2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@SequenceGenerator(name = "myUserSeq", sequenceName = "my_user_SEQ", allocationSize = 1)
@Data
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myUserSeq")
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "role")
    private String role;

    public MyUser setId(Long id) {
        this.id = id;
        return this;
    }

    public MyUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public MyUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public MyUser setRole(String role) {
        this.role = role;
        return this;
    }
}
