package com.oway.ui.trip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.oway.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinishTripActivity extends AppCompatActivity {
    @OnClick(R.id.btn_finish)
    public void onFinish() {
    finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_trip);
        ButterKnife.bind(this);
    }
}
