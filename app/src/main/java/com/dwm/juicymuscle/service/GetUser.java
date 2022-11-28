package com.dwm.juicymuscle.service;

import android.os.Handler;
import android.util.Log;

import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.model.User;

import java.io.IOException;

public class GetUser {

    private static User user;

    public static User get(String email){
        String[] field = new String[1];
        String[] data = new String[1];
        field[0] = "email";
        data[0] = email;
        PutData putData = new PutData("http://ulysseguillot.fr/apiLoginJuicyMuscle/getUser.php", "POST", field, data);
        if (putData.startPut()) {
            if(putData.onComplete()){
                String result = putData.getResult();
                ServiceApi jsonToObject = new ServiceApi();
                try {
                    user = jsonToObject.readJsonUser(result);
                    Log.d("erreur", result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }
}
