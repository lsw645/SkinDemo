package com.lxw.skindemo;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.yanzhenjie.permission.AndPermission;

import java.io.File;

public class MainActivity extends SkinActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .send();
    }

    public void jump(View view) {
        String path =new File( Environment.getExternalStorageDirectory()+File.separator+"skin.apk").getAbsolutePath();
        SkinManager.getInstance().loadSkin(path);
        update();
    }
}
