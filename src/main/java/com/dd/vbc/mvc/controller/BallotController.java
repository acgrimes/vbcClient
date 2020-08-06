package com.dd.vbc.mvc.controller;

import com.dd.vbc.mvc.model.BallotBean;
import com.dd.vbc.mvc.view.BallotView;
import com.dd.vbc.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.scene.Scene;

import java.util.List;

public class BallotController {

    private final Presenter presenter;
    private final BallotView ballotView;

    public BallotController(Presenter presenter) {
        this.presenter = presenter;
        ballotView = new BallotView(this);
    }

    public void handleRadioButtonAction(ActionEvent actionEvent) {

    }

    public Scene buildScene(List<BallotBean> ballotBeanList) {
        Scene theScene = null;
        try {
            theScene = ballotView.buildScene(ballotBeanList);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return theScene;
    }
}
