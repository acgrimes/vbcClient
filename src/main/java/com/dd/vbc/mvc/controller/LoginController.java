package com.dd.vbc.mvc.controller;

import com.dd.vbc.presenter.Presenter;
import com.dd.vbc.mvc.view.LoginView;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class LoginController {

    private final Presenter presenter;
    private LoginView loginView;
    private TextField username, password;

    public LoginController(Presenter presenter) {
        this.presenter = presenter;
    }

    public void init() {
        loginView = new LoginView(this);

    }

    public Scene buildScene() {
        return loginView.buildScene();
    }

    public void submitLogin(TextField email, TextField password) {
        presenter.submitLogin(email.getText(), password.getText());
    }

    public void clearText(TextField email, TextField password) {
        email.clear();
        password.clear();
    }

    public void clearText() {
        loginView.getEmail().clear();
        loginView.getPassword().clear();
    }
}
