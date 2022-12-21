package space_travel.client;

import jakarta.persistence.*;
import lombok.Data;
import space_travel.ticket.Ticket;

import java.util.List;

@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String name;
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
