package net.cpsec.zfwx.guodian.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;

public class CreateGroupActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        initView();
    }

    private void initView() {
        ((TextView) findViewById(R.id.back)).setOnClickListener(this);
        findViewById(R.id.confirm).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.confirm:

                break;
        }

    }
}
