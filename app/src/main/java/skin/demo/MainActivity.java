package skin.demo;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import skin.lib.BaseActivity;
import skin.lib.SkinManager;
import skin.lib.SkinTheme;
import skin.lib.Skinable.Skinnable;
import skin.lib.Skinable.TextColorAware;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.change_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.reSkin(SkinManager.theme == SkinTheme.DEFAULT ? SkinTheme.NIGHT : SkinTheme.DEFAULT);
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
        TextView tv = new TextView(this);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22f);
        tv.setText("This is a TextView added in code");
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics());
        tv.setPadding(padding, padding, padding, padding);
        linearLayout.addView(tv);
        SkinManager.add(new TextColorAware(tv, R.color.textColor));
    }
}
