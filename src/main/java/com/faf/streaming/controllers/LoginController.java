package com.faf.streaming.utils;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by schiduvasile on 6/6/17.
 */
public class LoginController {

    @FXML
    private TextField id;
    @FXML
    private TextField pass;


    public void getUserCretentials() {

        String userId = id.getText();
        String userPass = pass.getText();
        System.out.println("user: " + userId + " " + userPass);
    }

}
