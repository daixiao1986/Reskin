package skin.lib;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import skin.lib.item.ImageViewSrcItem;
import skin.lib.item.TextViewTextColorItem;
import skin.lib.item.ViewBackgroundItem;

/**
 * 实现LayoutInflater.Factory，通过实现其onCreateView()方法，手动创建好布局文件中的View。
 * 遍历自行实例化的View可记录需要修改主题的View及其属性，修改这些View的属性值即可实现换肤。
 * <p/>
 * Created by fengshzh on 1/20/16.
 */
public class SkinLayoutInflaterFactory implements LayoutInflater.Factory {
    private static final String TAG = "SkinLayoutInflaterFactory";

    private static final String[] sSuffix = {
            "",
            "_night"
    };

    private static final String ATTR_TEXT_COLOR = "textColor";
    private static final String ATTR_SRC = "src";
    private static final String ATTR_BACKGROUND = "background";

    private static final String RES_DRAWABLE = "drawable";
    private static final String RES_COLOR = "color";

    /**
     * 这几个前缀在xml布局文件中申明View时可省略，但是实例化View要使用Java反射机制调用其构造函数，需要补全类名
     * 前三个来自PhoneLayoutInflater，第四个来自LayoutInflater
     */
    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
    };


    Context mContext;
    LayoutInflater mLayoutInflater;

    private static List<ViewBackgroundItem> mViewBackgroundItems = new ArrayList<>();
    private static List<TextViewTextColorItem> mTextViewTextColorItems = new ArrayList<>();
    private static List<ImageViewSrcItem> mImageViewSrcItems = new ArrayList<>();

    public SkinLayoutInflaterFactory(Activity activity) {
        mContext = activity;
        mLayoutInflater = activity.getLayoutInflater();
    }

    /**
     * Hook you can supply that is called when inflating from a LayoutInflater.
     * You can use this to customize the tag names available in your XML
     * layout files.
     * <p/>
     * <p/>
     * Note that it is good practice to prefix these custom names with your
     * package (i.e., com.coolcompany.apps) to avoid conflicts with system
     * names.
     *
     * @param name    Tag name to be inflated.
     * @param context The context the view is being created in.
     * @param attrs   Inflation attributes as specified in XML file.
     * @return View Newly created view. Return null for the default
     * behavior.
     */
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        try {
            View view = createView(name, attrs);
            if (view != null) {
                addSkinViewIfNecessary(view, attrs);
                return view;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "Dangerous!!! You may miss view " + name);
        return null;
    }


    /**
     * 手动创建View
     * 只有返回的View不为空，才能保证记录到需要修改主题的View
     *
     * @param name  Tag name to be inflated.
     * @param attrs Inflation attributes as specified in XML file.
     * @return View Newly created view. Return null for the default behavior.
     * @throws ClassNotFoundException
     */
    private View createView(String name, AttributeSet attrs) throws ClassNotFoundException {
        // from PhoneLayoutInflater
        for (String prefix : sClassPrefixList) {
            try {
                View view = mLayoutInflater.createView(name, prefix, attrs);
                if (view != null) {
                    Log.d(TAG, "Inflate view success: " + name + ", " + attrs);
                    return view;
                }
            } catch (ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }

        return mLayoutInflater.createView(name, null, attrs);
    }

    /**
     * 记录需要修改主题的View及其属性
     *
     * @param view  需要修改主题的View
     * @param attrs 需要修改主题的View的属性
     */
    private void addSkinViewIfNecessary(View view, AttributeSet attrs) {

        // TODO 按View Type遍历还是按attrs遍历，效率待比较

        // 按attrs遍历
        int n = attrs.getAttributeCount();
        for (int i = 0; i < n; i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            int id = 0;
            if (attrValue.startsWith("@")) {
                id = Integer.parseInt(attrValue.substring(1));
            }

            switch (attrName) {
                case ATTR_BACKGROUND:
                    String typeName = mContext.getResources().getResourceTypeName(id);
                    mViewBackgroundItems.add(new ViewBackgroundItem(view, id, typeName));
                    break;
                case ATTR_TEXT_COLOR:
                    mTextViewTextColorItems.add(new TextViewTextColorItem((TextView) view, id));
                    break;
                case ATTR_SRC:
                    mImageViewSrcItems.add(new ImageViewSrcItem((ImageView) view, id));
                    break;
            }
        }
    }

    /**
     * 修改主题
     */
    public void reSkin() {
        // TODO: fengshzh 1/21/16 出意外不更新View的该属性

        for (TextViewTextColorItem item : mTextViewTextColorItems) {
            item.view.setTextColor(getColor(item.id, sSuffix[SkinManager.theme.ordinal()
                    ]));
        }

        for (ImageViewSrcItem item : mImageViewSrcItems) {
            item.view.setImageDrawable(getDrawable(item.id, sSuffix[SkinManager.theme.ordinal()
                    ]));
        }

        for (ViewBackgroundItem item : mViewBackgroundItems) {
            if (item.typeName.equals(RES_COLOR)) {
                item.view.setBackgroundColor(getColor(item.id, sSuffix[SkinManager.theme.ordinal()
                        ]));
            } else if (item.typeName.equals(RES_DRAWABLE)) {
                item.view.setBackgroundDrawable(getDrawable(item.id, sSuffix[SkinManager.theme
                        .ordinal()]));
            }
        }

    }

    /**
     * 获取新主题color值
     *
     * @param oldResId 资源id
     * @param suffix   新主题资源后缀名
     * @return 新主题color值
     */
    private int getColor(int oldResId, String suffix) {
        int newResId = getNewId(oldResId, suffix, RES_COLOR);
        return mContext.getResources().getColor(newResId);
    }

    /**
     * 获取新主题drawable值
     *
     * @param oldResId 资源id
     * @param suffix   新主题资源后缀名
     * @return 新主题drawable值
     */
    private Drawable getDrawable(int oldResId, String suffix) {
        int newResId = getNewId(oldResId, suffix, RES_DRAWABLE);
        return mContext.getResources().getDrawable(newResId);
    }

    private int getNewId(int oldResId, String suffix, String defType) {
        String oldResName = mContext.getResources().getResourceEntryName(oldResId);
        String newResName = oldResName + suffix;
        return mContext.getResources().getIdentifier(newResName, defType, mContext
                .getPackageName());
    }

    public void clear() {
        mViewBackgroundItems.clear();
        mTextViewTextColorItems.clear();
        mImageViewSrcItems.clear();
    }
}
