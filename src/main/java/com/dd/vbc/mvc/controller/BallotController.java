package com.dd.vbc.mvc.controller;

import com.dd.vbc.enums.BallotItemType;
import com.dd.vbc.mvc.model.Ballot;
import com.dd.vbc.mvc.model.BallotBean;
import com.dd.vbc.mvc.view.BallotView;
import com.dd.vbc.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.scene.Scene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BallotController {

    private final Presenter presenter;
    private final BallotView ballotView;
    private final Ballot ballot;

    public BallotController(Presenter presenter) {
        this.presenter = presenter;
        ballotView = new BallotView(this);
        final Map<String, String> office = new HashMap<>();
        final Map<String, String> amendment = new HashMap<>();
        final Map<String, String> question = new HashMap<>();
        ballot = new Ballot(UUID.randomUUID(), office, question);
    }

    public void handleRadioButtonAction(BallotItemType ballotItemType, String office, String candidate) {

        switch(ballotItemType) {
            case OFFICE: {
                ballot.getOfficeCandidate().put(office, candidate);
                break;
            }
            case QUESTION: {
                ballot.getQuestionAnswer().put(office, candidate);
                break;
            }
        }

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
