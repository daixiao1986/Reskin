package skin.demo;

import android.os.Bundle;
import android.view.View;

import skin.lib.BaseActivity;
import skin.lib.SkinManager;
import skin.lib.SkinTheme;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.change_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.reSkin(SkinTheme.NIGHT);
            }
        });
    }
}
