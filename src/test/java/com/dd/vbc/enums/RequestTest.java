package com.dd.vbc.enums;

import org.junit.jupiter.api.Test;

public class RequestTest {

    @Test
    void requestTest() {

        Request et = Request.ElectionTransaction;
        byte[] enumBytes = et.serialize();
        et.deserialize(enumBytes);

        System.out.println("Request enum is: "+et.name());
    }
}
