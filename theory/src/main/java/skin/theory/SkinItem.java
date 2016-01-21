package skin.theory;

import android.view.View;

/**
 * 需要换肤的单元，记录View及其属性
 * <p/>
 * Created by fengshzh on 1/20/16.
 */
public class SkinItem {
    View view;
    int id;

    public SkinItem(View view, int id) {
        this.view = view;
        this.id = id;
    }
}
