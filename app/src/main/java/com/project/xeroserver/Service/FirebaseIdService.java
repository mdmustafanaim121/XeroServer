package com.project.xeroserver.Service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.project.xeroserver.Common.Common;
import com.project.xeroserver.Model.Token;

public class FirebaseIdService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String tokenrefresh = FirebaseInstanceId.getInstance().getToken();
        updateToken(tokenrefresh);
    }

    private void updateToken(String tokenrefresh) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference tokens = db.getReference("Tokens");
        Token token = new Token(tokenrefresh,true); //false because this token is from Client App
        tokens.child(Common.currUser.getPhone()).setValue(token);
    }
    }

