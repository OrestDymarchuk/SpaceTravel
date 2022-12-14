package space_travel.client;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String name;
}
