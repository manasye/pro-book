package com.blackmamba.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

@WebService
@SOAPBinding(style= Style.RPC, use= SOAPBinding.Use.ENCODED)
public class BookWS {
    @WebMethod
    public String searchTitle(String title) {
        return "Hello, World JAX-WS " + title + " HUYUUUU";
    }

    @WebMethod
    public String searchDetail(String id) throws IOException {
        return "huyu";
    }

//    @WebMethod

}

