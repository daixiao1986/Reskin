package skin.lib;

import android.content.Context;
import android.content.res.Resources;

/**
 * 皮肤主题
 * <p/>
 * Created by fengshzh on 1/21/16.
 */
public enum SkinTheme {
    DEFAULT(""), NIGHT("_night");

    private String suffix;

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
