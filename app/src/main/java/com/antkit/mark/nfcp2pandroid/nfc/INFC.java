package com.antkit.mark.nfcp2pandroid.nfc;

import android.support.v7.app.AppCompatActivity;

/**
 * @author Mark
 * @Date on 2019/1/30
 **/
public interface INFC {

    void enableForegroundDispatch(AppCompatActivity activity);

    void disableForegroundDispatch();

}
