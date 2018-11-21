package com.blackmamba.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public interface HelloWorld {
    @WebMethod
    String getHelloWorldAsString(String name);
}
