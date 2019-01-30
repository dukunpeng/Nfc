package com.antkit.mark.nfcp2pandroid;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.antkit.mark.nfcp2pandroid.nfc.NFCStevedore;
import com.antkit.mark.nfcp2pandroid.nfc.NfcUtils;
import com.bumptech.glide.Glide;

import java.io.IOException;

/**
 * @author Mark
 * @Date on 2019/1/30
 **/
public class AndroidBeamMainActivity extends AppCompatActivity {

    private EditText mBeamText;

    private TextView name;
    private LinearLayout friendPan;
    private String friendName = "";
    private int user;
    private ImageView userImg;
    private ImageView ld;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        getSupportActionBar().hide();
        checkNfcBeam();
        mBeamText = findViewById(R.id.edittext_beam_text);
        name =  findViewById(R.id.name);
        userImg =  findViewById(R.id.userImg);
        friendPan =  findViewById(R.id.friendPan);
        ld =  findViewById(R.id.ld);
            Glide.with(this).load(R.drawable.ld).into(ld);
         friendPan.setVisibility(View.INVISIBLE);
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendPan.setVisibility(View.INVISIBLE);
                Toast.makeText(AndroidBeamMainActivity.this,"同意被添加",Toast.LENGTH_LONG).show();
                ld.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                friendPan.setVisibility(View.INVISIBLE);
                Toast.makeText(AndroidBeamMainActivity.this,"拒绝被添加",Toast.LENGTH_LONG).show();
                ld.setVisibility(View.VISIBLE);

            }
        });

        if (getIntent()!=null){
            user = getIntent().getIntExtra("user",0);
        }
        switch (user){
            case 0:
                userImg.setImageResource(R.drawable.a);
                mBeamText.setText("Mark");
                break;
            case 1:
                userImg.setImageResource(R.drawable.b);
                mBeamText.setText("summer");
                break;
        }

    }
    private void checkNfcBeam() {
        NfcAdapter  nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter !=null){
            if (!nfcAdapter.isEnabled()){
                //支持NFC，但是没有打开
                Intent intent = new Intent(Settings.ACTION_NFCSHARING_SETTINGS);
                startActivity(intent);
            }else if(!nfcAdapter.isNdefPushEnabled()){
                //API 16+
                Intent intent = new Intent(Settings.ACTION_NFCSHARING_SETTINGS);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onResume() {
        //启动NFC自动管理
        NFCStevedore.getInstance()
                .assemblePendingIntent(
                        PendingIntent.getActivity(
                                this, 0, new Intent(this,
                getClass()), 0))
                .assembleOriginalMessageEdit(mBeamText)
                .enableForegroundDispatch(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        NFCStevedore.getInstance().disableForegroundDispatch();
    }

    //接收到对面信息传输
    @Override
    public void onNewIntent(Intent intent) {

        ld.setVisibility(View.INVISIBLE);
        //解析数据
        friendName = NfcUtils.readNfcTag(intent);

        if (!TextUtils.isEmpty(friendName)){
           friendPan.setVisibility(View.VISIBLE);
           name.setText(friendName);
        }
    }





}