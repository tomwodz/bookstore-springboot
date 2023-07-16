package pl.camp.it.book.store;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("tomasz"));
    }
}
