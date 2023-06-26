package pl.camp.it.book.store;

import org.apache.commons.codec.digest.DigestUtils;
import pl.camp.it.book.store.model.Book;

public class Main {

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("admin"));
    }
}
