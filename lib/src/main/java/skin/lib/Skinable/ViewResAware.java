package skin.lib.Skinable;

import android.view.View;

import skin.lib.SkinTheme;

/**
 * Created by dfl on 2016/1/26.
 */
public abstract class ViewResAware<V extends View> extends SkinAware<V> {

    private int resId;

    public ViewResAware(V view, int resId) {
        super(view);
        this.resId = resId;
    }

    @Override
    public void reSkin(V view, SkinTheme theme) {
        reSkin(view, theme.getId(view.getContext(), resId));
    }

    abstract public void reSkin(V view, int resId);
}
