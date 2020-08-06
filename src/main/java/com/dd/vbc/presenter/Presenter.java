package com.dd.vbc.presenter;

import com.dd.vbc.enums.Response;
import com.dd.vbc.mvc.controller.BallotController;
import com.dd.vbc.mvc.controller.LoginController;
import com.dd.vbc.mvc.model.BallotBean;
import com.dd.vbc.mvc.model.FxmlBallot;
import com.dd.vbc.provider.Provider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Presenter extends Application {

    private final Provider provider;
    private final LoginController loginController;
    private final BallotController ballotController;
    private Stage primaryStage;
    private static Thread appThread;

    public Presenter() {
        super();
        provider = new Provider(this);
        loginController = new LoginController(this);
        ballotController = new BallotController(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        loginController.init();
        Scene loginScene = loginController.buildScene();
        String styleSheet = Presenter.class.getResource("styles.css").toExternalForm();
        loginScene.getStylesheets().add(styleSheet);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Voter Login");
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public void submitLogin(String username, String password) {
        provider.submitLogin(username, password);
    }

    public void submitBallotRequest() {
        provider.submitBallotRequest();
    }

    public void handleServerResponse(Response response) {

        switch(response) {
            case Authentication: {
                provider.submitBallotRequest();
                break;
            }
        }
    }

    public void handleServerResponse(List<BallotBean> ballotBeanList) {




    }

    public static void main(String[] args) {
        launch(args);
        appThread = Thread.currentThread();
    }
}
