package pl.camp.it.book.store.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderPosition {
    private Book book;

    private int quantity;

    public void incrementQuantity() {
        this.quantity++;
    }
}
