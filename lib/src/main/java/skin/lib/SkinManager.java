package skin.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import skin.lib.Skinable.SkinAware;
import skin.lib.Skinable.SkinWrapper;
import skin.lib.Skinable.Skinnable;

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

    // TODO: fengshzh 1/22/16 Activity弱引用
    /**
     * 添加到换肤管理器的Activity列表
     */
    static List<BaseActivity> activityList = new ArrayList<>();

    static List<SkinAware> skinnableList = new ArrayList<>();

    static WeakHashMap<Skinnable, SkinAware> skinAwareMap = new WeakHashMap<>();

    /**
     * 向换肤管理器注册Activity
     */
    public static void register(BaseActivity activity) {
        activityList.add(activity);
    }

    /**
     * 向换肤管理器反注册Activity
     */
    public static void unRegister(BaseActivity activity) {
        activityList.remove(activity);
    }

    public static void add(Skinnable skinnable) {
        if (skinnable != null) {
            SkinAware aware;
            if (skinnable instanceof SkinAware) {
                aware = (SkinAware) skinnable;
            } else {
                aware = skinAwareMap.containsKey(skinnable) ?
                        skinAwareMap.get(skinnable) : new SkinWrapper(skinnable);
                skinAwareMap.put(skinnable, aware);
            }
            if (skinnableList.indexOf(aware) < 0) {
                skinnableList.add(aware);
            }
            skinnable.reSkin(theme);
        }
    }

    public static void remove(Skinnable skinnable) {
        if (skinnable != null) {
            SkinAware aware;
            if (skinnable instanceof SkinAware) {
                aware = (SkinAware) skinnable;
            } else {
                aware = skinAwareMap.remove(skinnable);
            }
            skinnableList.remove(aware);
        }
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

        for (BaseActivity activity : activityList) {
            activity.reSkin();
        }
        for (Skinnable skinnable : skinnableList) {
            skinnable.reSkin(theme);
        }
    }

}
