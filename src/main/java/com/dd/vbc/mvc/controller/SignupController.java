package com.dd.vbc.mvc.controller;

import com.dd.vbc.presenter.Presenter;
import com.dd.vbc.mvc.view.LoginView;
import javafx.scene.control.TextField;

public class SignupController {

    private final Presenter presenter;
    private LoginView loginView;
    private TextField username, password, voterId;

    public SignupController(Presenter presenter) {
        this.presenter = presenter;
    }
}
