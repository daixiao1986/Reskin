package skin.lib.item;

import android.view.View;

/**
 * View background换肤item
 * <p/>
 * Created by fengshzh on 1/21/16.
 */
public class ViewBackgroundItem extends BaseSkinItem {
    // TODO: fengshzh 1/22/16 View弱引用？
    public View view;
    public String typeName;

    public ViewBackgroundItem(View view, int id, String typeName) {
        this.view = view;
        this.id = id;
        this.typeName = typeName;
    }
}
