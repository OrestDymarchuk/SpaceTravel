package space_travel.planet;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Planet {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    @Column()
    private String name;
}
