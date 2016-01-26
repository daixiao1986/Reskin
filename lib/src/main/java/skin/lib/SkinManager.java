package skin.lib;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 换肤管理器
 * <p/>
 * Created by fengshzh on 1/21/16.
 */
public class SkinManager {
    /**
     * 当前主题
     */
    public static SkinTheme theme = SkinTheme.DEFAULT;

    /**
     * 添加到换肤管理器的Activity列表
     */
    static List<WeakReference<BaseActivity>> activityList = new ArrayList<>();

    /**
     * 向换肤管理器注册Activity
     */
    public static void register(BaseActivity activity) {
        activityList.add(new WeakReference<>(activity));
    }

    /**
     * 向换肤管理器反注册Activity
     */
    public static void unRegister(BaseActivity activity) {
        activityList.remove(activity);
    }

    /**
     * 触发换肤
     *
     * @param newTheme 换肤目标主题
     */
    public static void reSkin(SkinTheme newTheme) {
        if (newTheme == theme) {
            return;
        }

        theme = newTheme;

        for (WeakReference<BaseActivity> ref : activityList) {
            BaseActivity activity = ref.get();
            if (activity != null) {
                activity.reSkin();
            }
        }
    }

}
