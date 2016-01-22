package skin.lib.item;

import android.view.View;

/**
 * Created by fengshzh on 1/21/16.
 */
public class ViewBackgroundItem extends BaseSkinItem {
    public View view;
    public String typeName;

    public ViewBackgroundItem(View view, int id, String typeName) {
        this.view = view;
        this.id = id;
        this.typeName = typeName;
    }
}
