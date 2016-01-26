package skin.lib.Skinable;

import android.view.View;

/**
 * Created by dfl on 2016/1/26.
 */
public class BackgroundAware extends ViewResAware<View> {

    public BackgroundAware(View view, int resId) {
        super(view, resId);
    }

    @Override
    public void reSkin(View view, int resId) {
        view.setBackgroundResource(resId);
    }
}
