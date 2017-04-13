package com.lxw.skindemo;

import android.view.View;
import android.widget.TextView;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/13
 * @see //TODO
 * @since JDK 1.8
 */

public class TextSkin extends SkinInterface {

    public TextSkin(String attrName, int defId, String attrValueName, String attrType) {
        super(attrName, defId, attrValueName, attrType);
    }

    @Override
    public void apply(View view) {
            if(view instanceof TextView){
                TextView textView= (TextView) view;
                textView.setTextColor(SkinManager.getInstance().getColor(defId));
            }
    }
}
