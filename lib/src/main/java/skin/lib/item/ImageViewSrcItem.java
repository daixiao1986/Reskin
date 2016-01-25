package skin.lib.item;

import android.widget.ImageView;

/**
 * ImageView src属性换肤item
 * <p/>
 * Created by fengshzh on 1/21/16.
 */
public class ImageViewSrcItem extends BaseSkinItem {
    public ImageView view;

    public ImageViewSrcItem(ImageView view, int id) {
        this.view = view;
        this.id = id;
    }
}
