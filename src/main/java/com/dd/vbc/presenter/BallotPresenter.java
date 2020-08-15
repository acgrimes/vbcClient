package com.dd.vbc.presenter;

import com.dd.vbc.mvc.controller.BallotController;
import com.dd.vbc.mvc.model.BallotBean;
import com.dd.vbc.mvc.model.FxmlBallot;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class BallotPresenter implements Runnable {

    private final List<BallotBean> ballotBeanList;
    private final BallotController ballotController;
    private final Stage primaryStage;

    public BallotPresenter(Stage primaryStage, List<BallotBean> ballotBeanList, BallotController ballotController) {
        this.primaryStage = primaryStage;
        this.ballotBeanList = ballotBeanList;
        this.ballotController = ballotController;
    }

    public void run() {
        try {
            System.out.println("BallotPresenter.run(List<BallotBean>)");
            Scene ballotScene = ballotController.buildScene(ballotBeanList);

            URL cssURL = BallotPresenter.class.getResource("styles.css");
            String styleSheet = cssURL.toExternalForm();
            ballotScene.getStylesheets().add(styleSheet);

            this.primaryStage.setScene(ballotScene);
            this.primaryStage.setTitle("Ballot");
            this.primaryStage.show();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
