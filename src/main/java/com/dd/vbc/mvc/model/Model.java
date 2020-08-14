package com.dd.vbc.mvc.model;

import com.dd.vbc.enums.Request;
import com.dd.vbc.network.ElectionRequest;
import com.dd.vbc.network.ElectionResponse;
import com.dd.vbc.network.NettyMessage;

import java.io.IOException;
import java.util.UUID;

public class Model {

    private Voter voter;

    public Model() { }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public void submitLogin(String username, String password) {

        ElectionRequest electionRequest = new ElectionRequest();
        electionRequest.setRequest(Request.Login);
        LoginCredentials credentials = new LoginCredentials(username, password);
        electionRequest.setLoginCredentials(credentials);

        NettyMessage.setMessage(electionRequest.serialize());
    }

    public void submitBallotRequest() {

        ElectionRequest electionRequest = new ElectionRequest();
        electionRequest.setRequest(Request.BallotRequest);
        voter = new Voter(123456789L, UUID.randomUUID(), null);
        electionRequest.setVoter(voter);
        NettyMessage.setMessage(electionRequest.serialize());

    }

    public ElectionResponse getElectionResponse(byte[] byteResponse) {
        ElectionResponse electionResponse = new ElectionResponse();
        electionResponse.deserialize(byteResponse);
        return electionResponse;
    }
}
