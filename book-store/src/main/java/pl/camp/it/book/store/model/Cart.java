package pl.camp.it.book.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
public class Cart {

private  final Set<OrderPosition> positions = new HashSet<>();

}
