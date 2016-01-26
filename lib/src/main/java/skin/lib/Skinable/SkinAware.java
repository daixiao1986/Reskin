package skin.lib.Skinable;

import java.lang.ref.WeakReference;

import skin.lib.SkinTheme;

/**
 * Created by dfl on 2016/1/26.
 */
public abstract class SkinAware<T> implements Skinnable {

    private WeakReference<T> ref;

    public SkinAware(T dest) {
        this.ref = new WeakReference<>(dest);
    }

    @Override
    public void reSkin(SkinTheme theme) {
        T dest = ref.get();
        if (dest == null) {
            return;
        }
        reSkin(dest, theme);
    }

    abstract public void reSkin(T dest, SkinTheme theme);
}
