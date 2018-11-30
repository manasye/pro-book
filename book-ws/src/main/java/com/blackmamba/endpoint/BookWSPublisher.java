package com.blackmamba.endpoint;

import javax.xml.ws.Endpoint;
import com.blackmamba.ws.BookWS;

public class BookWSPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/book", new BookWS());
    }
}
