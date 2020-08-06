package com.dd.vbc.presenter;

import com.dd.vbc.mvc.controller.BallotController;
import com.dd.vbc.mvc.model.FxmlBallot;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BallotPresenter implements Runnable {

    private FxmlBallot fxmlBallot;
    private BallotController ballotController;
    private Stage primaryStage;

    public BallotPresenter(Stage primaryStage, FxmlBallot fxmlBallot, BallotController ballotController) {
        this.primaryStage = primaryStage;
        this.fxmlBallot = fxmlBallot;
        this.ballotController = ballotController;
    }

    public void run() {
        try {
            Stage theStage = new Stage();
            theStage.setTitle("Ballot");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(this);
            GridPane gridPane = fxmlLoader.load(new ByteArrayInputStream(fxmlBallot.getFile()));
//            theStage.setScene(ballotController.buildScene(gridPane));
            theStage.show();

            primaryStage.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
