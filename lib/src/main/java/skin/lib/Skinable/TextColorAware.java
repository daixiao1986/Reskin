package skin.lib.Skinable;

import android.widget.TextView;

/**
 * Created by dfl on 2016/1/26.
 */
public class TextColorAware extends ViewResAware<TextView> {

    public TextColorAware(TextView view, int resId) {
        super(view, resId);
    }

    @Override
    public void reSkin(TextView view, int resId) {
        view.setTextColor(view.getResources().getColor(resId));
    }
}
