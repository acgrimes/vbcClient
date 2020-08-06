package com.dd.vbc.enums;

import com.dd.vbc.mvc.model.Serialization;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Response {
    Authorization,
    Authentication,
    CompletedBallot,
    Ballot,
    BallotAccepted,
    Last;

    private static final Map<Integer, Response> lookup = new HashMap<Integer, Response>();

    static{
        int ordinal = 0;
        for (Response response : EnumSet.allOf(Response.class)) {
            lookup.put(ordinal, response);
            ordinal+= 1;
        }
    }

    public final static Response fromOrdinal(int ordinal) {
        return lookup.get(ordinal);
    }

    public byte[] serialize() {
        Serialization serial = new Serialization();
        byte[] responseBytes = serial.serializeInt(ordinal());
        return responseBytes;
    }

    public static Response deserialize(byte[] response) {
        Serialization serial = new Serialization();
        int enumValue = serial.deserializeInt(response);
        return Response.fromOrdinal(enumValue);
    }
}
