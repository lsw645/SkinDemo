package com.lxw.skindemo;

import android.view.View;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/13
 * @see //TODO
 * @since JDK 1.8
 */

public class BackgroundSkin extends SkinInterface {
    public BackgroundSkin(String attrName, int defId, String attrValueName, String attrType) {
        super(attrName, defId, attrValueName, attrType);
    }

    @Override
    public void apply(View view) {
        if ("color".equals(attrType)) {
            view.setBackgroundColor(SkinManager.getInstance().getColor(defId));
        } else if ("drawable".equals(attrType)) {
            view.setBackgroundDrawable(SkinManager.getInstance().getDrawable(defId));
        }
    }
}
