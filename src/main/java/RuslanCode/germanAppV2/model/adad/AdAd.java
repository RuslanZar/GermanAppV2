package RuslanCode.germanAppV2.model.adad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "adad")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(name = "adadSeq", sequenceName = "adad_SEQ", allocationSize = 1)
public class AdAd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "adadSeq")
    @Column(name = "id")
    private Long id;
    @Column(name = "russian")
    private String russian;
    @Column(name = "german")
    private String german;

    public AdAd(String russian, String german) {
        this.russian = russian;
        this.german = german;
    }
}
