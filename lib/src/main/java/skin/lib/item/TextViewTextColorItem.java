package skin.lib.item;

import android.widget.TextView;

/**
 * Created by fengshzh on 1/21/16.
 */
public class TextViewTextColorItem extends BaseSkinItem {
    public TextView view;

    public TextViewTextColorItem(TextView view, int id) {
        this.view = view;
        this.id = id;
    }
}
