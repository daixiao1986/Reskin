package skin.lib.Skinable;

import android.widget.ImageView;

/**
 * Created by dfl on 2016/1/26.
 */
public class ImageAware extends ViewResAware<ImageView> {

    public ImageAware(ImageView view, int resId) {
        super(view, resId);
    }

    @Override
    public void reSkin(ImageView view, int resId) {
        view.setImageResource(resId);
    }
}
