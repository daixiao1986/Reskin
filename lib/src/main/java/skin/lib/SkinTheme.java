package skin.lib;

import android.content.Context;
import android.content.res.Resources;

/**
 * 皮肤主题，支持的皮肤主题和资源后缀在次设置
 * <p/>
 * Created by fengshzh on 1/21/16.
 */
public enum SkinTheme {

    /**
     * 默认模式
     */
    DEFAULT(""),

    /**
     * 夜间模式
     */
    NIGHT("_night");


    /**
     * 主题资源后缀名
     */
    String suffix;

    SkinTheme(String suffix) {
        this.suffix = suffix;
    }

    public int getId(Context context, int resId) {
        Resources resources = context.getResources();
        String oldResName = resources.getResourceEntryName(resId);
        String typeName = resources.getResourceTypeName(resId);
        String newResName = oldResName + suffix;
        return resources.getIdentifier(newResName, typeName, context.getPackageName());
    }
}

