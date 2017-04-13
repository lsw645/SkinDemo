package com.lxw.skindemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/13
 * @see //TODO
 * @since JDK 1.8
 */

public class SkinActivity extends Activity {
    private SkinFactory mSkinFactory;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().init(this);
        mSkinFactory=new SkinFactory();
        LayoutInflaterCompat.setFactory(getLayoutInflater(),mSkinFactory);
    }

    public void update(){
        mSkinFactory.update();
    }
}
