package skin.lib;

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
}

