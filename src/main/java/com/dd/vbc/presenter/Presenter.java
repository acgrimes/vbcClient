package com.dd.vbc.presenter;

import com.dd.vbc.enums.Response;
import com.dd.vbc.mvc.controller.BallotController;
import com.dd.vbc.mvc.controller.LoginController;
import com.dd.vbc.mvc.model.BallotBean;
import com.dd.vbc.network.ElectionResponse;
import com.dd.vbc.provider.Provider;
import com.dd.vbc.provider.binding.ElectionResponseObservable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Presenter extends Application {

    private final Provider provider;
    private final LoginController loginController;
    private final BallotController ballotController;
    private final ElectionResponseObservable electionResponseObservable;
    private Stage primaryStage;
    private static Thread appThread;

    public Presenter() {
        super();
        provider = new Provider(this);
        loginController = new LoginController(this);
        ballotController = new BallotController(this);
        HandleServerResponse handleServerResponse = new HandleServerResponse();
        electionResponseObservable = new ElectionResponseObservable();
        electionResponseObservable.addListener(handleServerResponse);
    }

    @Override
    public void start(Stage stage) throws Exception {

        appThread = Thread.currentThread();
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
        provider.submitLogin(username, password, electionResponseObservable);
    }

    public void submitBallotRequest() {

    }

    public void handleServerResponse(Response response) {

        System.out.println("Presenter.handleServerResponse("+response.name()+")");
        switch(response) {
            case Authentication: {
                provider.submitBallotRequest(electionResponseObservable);
                break;
            }
        }
    }

    public void handleServerResponse(List<BallotBean> ballotBeanList) {

        BallotPresenter ballotPresenter = new BallotPresenter(primaryStage, ballotBeanList, ballotController);
        Platform.runLater(ballotPresenter);

    }



    public static void main(String[] args) {
        launch(args);

    }

    public class HandleServerResponse implements ChangeListener<ElectionResponse> {

        @Override
        public void changed(ObservableValue<? extends ElectionResponse> observableValue, ElectionResponse oldResponse, ElectionResponse newResponse) {
            switch(newResponse.getResponse()) {
                case Authentication: {
                    handleServerResponse(newResponse.getResponse());
                    break;
                }
                case Authorization: {

                    break;
                }
                case Ballot: {
                    handleServerResponse(newResponse.getBallotBeanList());
                    break;
                }
            }
        }
    }
}
