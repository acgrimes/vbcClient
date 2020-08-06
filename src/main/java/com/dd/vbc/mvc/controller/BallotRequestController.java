package com.dd.vbc.mvc.controller;

import com.dd.vbc.presenter.Presenter;
import com.dd.vbc.mvc.view.BallotRequestView;
import javafx.scene.Scene;

public class BallotRequestController {

    private BallotRequestView ballotRequestView;
    private final Presenter presenter;

    public BallotRequestController(Presenter presenter) {
        this.presenter = presenter;
    }

    public void init() {
        ballotRequestView = new BallotRequestView(this);

    }

    public Scene buildScene() {
        return ballotRequestView.buildScene();
    }

    public void submitBallotRequest() {
        presenter.submitBallotRequest();
    }

}
