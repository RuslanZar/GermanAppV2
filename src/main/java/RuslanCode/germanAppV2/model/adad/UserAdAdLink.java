package RuslanCode.germanAppV2.model.adad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user_adad_links")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(name = "userAdADSeq", sequenceName = "user_adad_SEQ", allocationSize = 1)
public class UserAdAdLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userAdADSeq")
    @Column(name = "id")
    Long id;
    @Column(name = "userID")
    private Long userID;
    @Column(name = "AdAdID")
    private Long adAdId;
    @Column(name = "score")
    private double score;
}
