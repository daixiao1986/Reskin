package skin.lib.item;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * View background换肤item
 * <p/>
 * Created by fengshzh on 1/21/16.
 */
public class ViewBackgroundItem extends BaseSkinItem {
    public WeakReference<View> view;
    public String typeName;

    public ViewBackgroundItem(View view, int id, String typeName) {
        this.view = new WeakReference<>(view);
        this.id = id;
        this.typeName = typeName;
    }
}
