package skin.lib;

import android.app.Activity;
import android.os.Bundle;

/**
 * 换肤基类Activity
 * <p/>
 * Created by fengshzh on 1/21/16.
 */
public abstract class BaseActivity extends Activity {

    private SkinLayoutInflaterFactory skinLayoutInflaterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SkinManager.register(this);

        skinLayoutInflaterFactory = new SkinLayoutInflaterFactory(this);
        getLayoutInflater().setFactory(skinLayoutInflaterFactory);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.unRegister(this);

        skinLayoutInflaterFactory.clear();
    }

    public void reSkin() {
        skinLayoutInflaterFactory.reSkin();
    }

}
