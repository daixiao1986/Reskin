package skin.lib.Skinable;

import skin.lib.SkinTheme;

/**
 * Created by dfl on 2016/1/26.
 */
public class SkinWrapper extends SkinAware<Skinnable> {

    public SkinWrapper(Skinnable dest) {
        super(dest);
    }

    @Override
    public void reSkin(Skinnable dest, SkinTheme theme) {
        dest.reSkin(theme);
    }
}
