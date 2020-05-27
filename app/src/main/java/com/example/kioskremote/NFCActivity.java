package com.example.kioskremote;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NFCActivity extends AppCompatActivity {
    private NfcAdapter nfcAdapter;//nfc정보 읽어옴
    private PendingIntent pendingIntent;//다른 액티비티에 전송할 intent 값을 저장
    private IntentFilter[] mIntentFilters;//intent filter
    private String[][] mNFCTechLists;//연결 단말기 정보 저장
    private TextView text;//단말기의 NFC 상태를 알려주는 텍스트 뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        //단말기의 nfc 상태를 점검
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null) {
            text.setText("NFC STATE : OFF");
        } else {
            text.setText("NFC STATE : ON");
        }

        //intent 지정
        Intent intent = new Intent(this, MenuActivity.class);//인텐트를 다른 액티비티에서 처리하는 방법
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);//intent값을 다음 엑티비티에 넘겨서 처리할 수 있도록 값을 넣어준다.

        //intentFilter setting
        IntentFilter ndefIntent = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);//NDEF와 함께 태그가 발견된 경우 액티비티를 시작하도록 필터링
        try {
            ndefIntent.addDataType("*/*");
        } catch (Exception e) {
            Log.e("Tag", e.toString());
        }

        mIntentFilters = new IntentFilter[]{ndefIntent,};

        //NFCtech setting
        mNFCTechLists = new String[][]{new String[]{NfcF.class.getName()}};
    }

    /*
    화면에 액티비티 올리기 전에 실행될 것들 설정
    다른 액티비티로 정보를 넘긴다.
    nfcAdapter.enableForegroundDispatch(this,pendingIntent, mIntentFilters,mNFCTechLists)를 이용해서 지정된 필터의 값만 넘겨준다.
     */
    @Override
    protected void onResume() {
        super.onResume();

        //NFC 사용 가능하면 작동.
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, mIntentFilters, mNFCTechLists);
        }
    }

    /*
    화면에 액티비티 안뜨면 그를 대처하는 내용.
    NFC 신호 대기를 중지 후 종료
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);//화면이 중지된다면 사용 안함.
            finish();
        }
    }
}
