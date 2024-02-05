package net;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class UrlTest {
    public static void main(String[] args) throws Exception {
        URI uri = URI.create("https://www.google.com");
        URL url = uri.toURL();

        try (InputStream inputStream = url.openStream()) {
            byte[] bytes = inputStream.readAllBytes();
            String s = new String(bytes);
            System.out.println("result = " + s);
        }
    }
}
