package pl.camp.it.book.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity(name ="torder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
    private final Set<OrderPosition> orderPositions = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Status status;
    private double total;
    private LocalDateTime dateTime;

    @JsonIgnore
    public String getPrettyTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return this.dateTime.format(formatter);
    }

    public  void addOrderPosition(OrderPosition orderPosition){
        this.orderPositions.add(orderPosition);
        orderPosition.setOrder(this);
    }

    public enum Status{
        NEW,
        PAID,
        SENT,
        DONE
    }

    public Order(int id) {
        this.id = id;
    }
}
