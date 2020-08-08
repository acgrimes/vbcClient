package com.dd.vbc.network;

import com.dd.vbc.enums.Request;
import com.dd.vbc.mvc.model.LoginCredentials;
import com.dd.vbc.mvc.model.Serialization;
import com.dd.vbc.mvc.model.Voter;

import java.io.*;
import java.util.Arrays;

public class ElectionRequest extends Serialization implements Serializable {

    private Request request;
    private LoginCredentials loginCredentials;
    private Voter voter;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public LoginCredentials getLoginCredentials() {
        return loginCredentials;
    }

    public void setLoginCredentials(LoginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public byte[] serialize0() {

        byte[] bytes = null;
        switch(request) {
            case Login: {
                if(loginCredentials!=null) {
                    bytes = concatenateBytes(request.serialize(), loginCredentials.serialize());
                    break;
                }
            }
            case BallotRequest: {
                if(voter!=null) {
                    bytes = concatenateBytes(request.serialize(), voter.serialize());
                    break;
                }
            }
        }
        return bytes;
    }

    public void deserialize0(byte[] byteRequest) {

        request = Request.fromOrdinal(deserializeInt(Arrays.copyOfRange(byteRequest, 0, 4)));
        switch(request) {
            case Login: {
                loginCredentials = new LoginCredentials();
                loginCredentials.deserialize(Arrays.copyOfRange(byteRequest, 4, byteRequest.length));
                break;
            }
            case BallotRequest: {
                voter = new Voter();
                voter.deserialize(Arrays.copyOfRange(byteRequest, 4, byteRequest.length));
                break;
            }
        }
    }

    public static byte[] serialize(ElectionRequest electionRequest) {

        byte[] result = null;
        try(final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final ObjectOutput out = new ObjectOutputStream(bos);) {
            out.writeObject(electionRequest);
            result = bos.toByteArray();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    public static ElectionRequest deserialize(byte[] objectStream) {

        ElectionRequest electionRequest = null;
        try(ByteArrayInputStream bis = new ByteArrayInputStream(objectStream);
            ObjectInput in = new ObjectInputStream(bis)) {
            electionRequest = (ElectionRequest) in.readObject();;
        } catch(IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
        return electionRequest;
    }

}
