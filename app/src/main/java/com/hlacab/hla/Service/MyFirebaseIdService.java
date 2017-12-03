package com.hlacab.hla.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.hlacab.hla.common.Common;
import com.hlacab.hla.model.Token;

/**
 * Created by gopinath on 03/12/17.
 */

public class MyFirebaseIdService extends FirebaseInstanceIdService {

    public void onTokenRefresh() {

        super.onTokenRefresh();

        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        updateTokenToServer(refreshToken);
    }

    private void updateTokenToServer(String refreshedToken) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference tokens = db.getReference(Common.token_tb1);

        Token token = new Token(refreshedToken);
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            tokens.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

}
