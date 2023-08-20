package pl.camp.it.book.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="torderposition")
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Book book;
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Order order;

    //TODO
    public OrderPosition(int id, Book book, int quantity) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }
}
