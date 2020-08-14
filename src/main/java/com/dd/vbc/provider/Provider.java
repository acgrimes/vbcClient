package com.dd.vbc.provider;

import com.dd.vbc.mvc.model.Model;
import com.dd.vbc.network.ElectionResponse;
import com.dd.vbc.network.VBCMobileClient;
import com.dd.vbc.presenter.Presenter;
import com.dd.vbc.provider.binding.ElectionResponseObservable;
import io.netty.bootstrap.Bootstrap;

public final class Provider {

    private VBCMobileClient vbcMobileClient;
    private Model model;
    private final Presenter presenter;
    private ElectionResponseObservable electionResponseObservable;

    public Provider(Presenter presenter) {
        this.presenter =  presenter;
        vbcMobileClient = new VBCMobileClient(this,"192.168.0.8", 61005);
        model = new Model();
    }

    public void submitLogin(String username, String password, ElectionResponseObservable electionResponseObservable) {
        this.electionResponseObservable = electionResponseObservable;
        model.submitLogin(username, password);
        Bootstrap bootstrap = vbcMobileClient.start();
//        try {
//            Bootstrap bootstrap = vbcMobileClient.start();
//            ChannelFuture channelFuture = bootstrap.connect().sync();
//            channelFuture.channel().closeFuture().sync();
//            channelFuture.addListener(ChannelFutureListener.CLOSE);
//        } catch(InterruptedException ie) {
//            ie.printStackTrace();
//        }
    }

    public void submitBallotRequest(ElectionResponseObservable electionResponseObservable) {

        this.electionResponseObservable = electionResponseObservable;
        model.submitBallotRequest();
        Bootstrap bootstrap = vbcMobileClient.start();

    }

    public void handleServerResponse(byte[] serverResponse) {

        ElectionResponse  electionResponse = model.getElectionResponse(serverResponse);
        System.out.println("Server response type: "+electionResponse.getResponse().name());
        electionResponseObservable.set(electionResponse);

    }
}
