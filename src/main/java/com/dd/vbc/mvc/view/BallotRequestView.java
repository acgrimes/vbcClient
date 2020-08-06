package com.dd.vbc.mvc.view;

import com.dd.vbc.mvc.controller.BallotRequestController;
import com.dd.vbc.presenter.Presenter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BallotRequestView {

    private final BallotRequestController ballotRequestController;

    public BallotRequestView(BallotRequestController ballotRequestController) {
        this.ballotRequestController = ballotRequestController;
    }

    public Scene buildScene() {

        //Creating Buttons
        Button submit = new Button("Request Ballot");
        submit.setId("submit");
        submit.setOnAction(event -> ballotRequestController.submitBallotRequest());

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(400, 200);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(submit, 0, 2);

        gridPane.setStyle("-fx-background-color: BEIGE;");

        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add(Presenter.class.getResource("styles.css").toExternalForm());
        return scene;

    }
}
