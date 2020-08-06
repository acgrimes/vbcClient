package com.dd.vbc.mvc.view;

import com.dd.vbc.mvc.controller.LoginController;
import com.dd.vbc.presenter.Presenter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class LoginView {

    private LoginController loginController;
    private TextField email = null;
    private PasswordField password = null;

    public TextField getEmail() {
        return email;
    }

    public PasswordField getPassword() {
        return password;
    }

    public LoginView(LoginController loginController) {
        this.loginController = loginController;
    }

    public Scene buildScene() {
        //creating label email
        Text emailLabel = new Text("Email");

        //creating label password
        Text passwordLabel = new Text("Password");

        //Creating Text Filed for email
        email = new TextField();

        //Creating Text Filed for password
        Tooltip pwTooltip = new Tooltip("Password must have uppercase, lowercase, number and a special character[@#$%&-!]");
        password = new PasswordField();
        password.setTooltip(pwTooltip);

        //Creating Buttons
        Button submit = new Button("Submit");
        submit.setId("submit");
        submit.setOnAction(event -> loginController.submitLogin(email, password));
        Button clear = new Button("Clear");
        clear.setId("clear");
        clear.setOnAction(actionEvent -> loginController.clearText(email, password));

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
        gridPane.add(emailLabel, 0, 0);
        gridPane.add(email, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(password, 1, 1);
        gridPane.add(submit, 0, 2);
        gridPane.add(clear, 1, 2);

        gridPane.setStyle("-fx-background-color: BEIGE;");

        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add(Presenter.class.getResource("styles.css").toExternalForm());
        return scene;
    }
}
