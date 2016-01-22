package skin.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengshzh on 1/21/16.
 */
public class SkinManager {
    public static SkinTheme theme = SkinTheme.DEFAULT;

    static List<BaseActivity> activityList = new ArrayList<>();

    public static void register(BaseActivity activity) {
        activityList.add(activity);
    }

    public static void unRegister(BaseActivity activity) {
        activityList.remove(activity);
    }

    public static void reSkin(SkinTheme newTheme) {
        if (newTheme == theme) {
            return;
        }

        theme = newTheme;

        for (BaseActivity activity : activityList) {
            activity.reSkin();
        }
    }

}
