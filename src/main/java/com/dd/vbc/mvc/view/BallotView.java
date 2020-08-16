package com.dd.vbc.mvc.view;

import com.dd.vbc.mvc.controller.BallotController;
import com.dd.vbc.mvc.model.BallotBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import java.util.List;
import java.util.stream.Collectors;

public class BallotView {

    private final BallotController ballotController;

    public BallotView(BallotController ballotController) {
        this.ballotController = ballotController;
    }

    public Scene buildScene(List<BallotBean> ballotBeanList) {

        List<GridPane> pages = buildBallotGridPanes(ballotBeanList);

        AnchorPane anchor = new AnchorPane();
        anchor.setStyle("-fx-background-color: BEIGE;");
        Pagination pagination = new Pagination(3, 0);
        pagination.setStyle("-fx-background-color: BEIGE;");
        pagination.setPageFactory((pageIndex) -> { return pages.get(pageIndex);});
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        anchor.prefHeight(600.0);
        anchor.prefWidth(400.0);
        anchor.getChildren().addAll(pagination);
        Scene scene = new Scene(anchor, 640, 480);
        return scene;
    }

    private List<GridPane> buildBallotGridPanes(List<BallotBean> ballotBeanList) {

        List<GridPane> gridPaneList = ballotBeanList.stream().
            map(ballotBean -> {
                GridPane gridPane = new GridPane();
                gridPane.setId("GridPane");
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.setPadding(new Insets(25, 25, 25, 25));
                gridPane.setStyle("-fx-background-color: BEIGE;");
                VBox headerVBox = new VBox(10);
                headerVBox.getStyleClass().add("vbox");
                Label headerLabel = new Label(ballotBean.getDescription());
                headerLabel.setWrapText(true);
                headerLabel.setId("headerLabel");
                headerVBox.getChildren().add(headerLabel);
                headerVBox.setAlignment(Pos.CENTER);
                List<VBox> officeBeanList = ballotBean.getOfficeBeanList().stream().
                     map(officeBean -> {
                        Label officelabel = new Label(officeBean.getOffice());
                        officelabel.setId("officeLabel");
                        ToggleGroup rbToggleGroup = new ToggleGroup();
                        VBox officeVBox = new VBox(25);
                        officeVBox.getChildren().add(officelabel);
                        officeVBox.getStyleClass().add("vbox");
                        List<RadioButton> rbList = officeBean.getCandidates().stream().
                            map(candidate -> {
                                RadioButton rb = new RadioButton(candidate);
                                rb.setToggleGroup(rbToggleGroup);
                                rb.setSelected(false);
                                rb.setOnAction(eh -> {
                                    ballotController.handleRadioButtonAction(officeBean.getBallotItemType(), officeBean.getOffice(), candidate);
                                });
                                return rb;
                            }).collect(Collectors.toList());
                        for(RadioButton rb : rbList) {
                            officeVBox.getChildren().add(rb);
                        }
                        return officeVBox;
                    }).collect(Collectors.toList());
                    gridPane.add(headerVBox, 0, 0);
                    gridPane.setColumnSpan(headerVBox, 1);
                    gridPane.setRowSpan(headerVBox, 1);
                    int count = 0;
                    for(VBox officeVBox : officeBeanList) {
                        gridPane.add(officeVBox, 0, ++count);
                        gridPane.setColumnSpan(officeVBox, 1);
                        gridPane.setRowSpan(officeVBox, 1);
                    }
                    return gridPane;
                }).collect(Collectors.toList());
            return gridPaneList;
    }
}
