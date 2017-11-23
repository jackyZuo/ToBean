package com.zxy.tobean;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    //假数据
    private String data = "{\"resultCode\": \"0\",\"resultMessage\": \"处理成功\",\"resultObject\": {\"appId\": \"demoId\",\"appName\": \"Demo\",\"banners\": [{\"id\": \"141\",\"link\": \"urlabc\",\"order\": \"5\",\"src\": \"load.png\"},{\"id\": \"283\",\"link\": \"urlefg\",\"order\": \"4\",\"src\": \"load3.jpg\"}],\"changeLog\": \"\",\"forceUpdate\": \"Y\",\"status\": \"1\",\"subApps\": [{\"icon\": \"abc.png\",\"link\": \"com.wifipay.action.abc\",\"name\": \"类目一\",\"order\": \"2\",\"status\": \"1\",\"type\": \"NATIVE_VIEW\",\"version\": \"1.2.4\"}],\"version\": \"1.3.6\",\"versionName\": \"1.3.6\"}}";
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(this);
        mTv =  findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        HomeInfoResp resp = (HomeInfoResp) Json2BeanUtil.getInstance().tobean(data, HomeInfoResp.class);
        mTv.setText(resp.toString());
    }
}
