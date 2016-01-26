package skin.lib;

import android.content.Context;

/**
 * 持久化存储当前主题
 * <p/>
 * Created by fengshzh on 1/26/16.
 */
public class SkinPreference {

    static Context context;

    static final String PREF_FILE = "theme";
    static final String KEY_THEME = "key_theme";

    /**
     * 设置Android Appllication Context，操作SharedPreference
     *
     * @param applicationContext Appllication Context, NOT Activity Context!
     */
    public static void init(Context applicationContext) {
        context = applicationContext;
    }

    /**
     * 获取当前主题
     */
    public static SkinTheme getTheme() {
        return SkinTheme.values()[context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
                .getInt(KEY_THEME, SkinTheme.DEFAULT.ordinal())];
    }

    /**
     * 设置新主题
     *
     * @param skinTheme 新主题
     */
    public static void setTheme(SkinTheme skinTheme) {
        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).edit()
                .putInt(KEY_THEME, skinTheme.ordinal())
                .apply();
    }
}
