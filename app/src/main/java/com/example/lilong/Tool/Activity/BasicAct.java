package com.example.lilong.Tool.Activity;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lilong.Content.Utils.ActivityCollector;
import com.example.lilong.R;
import com.example.lilong.Tool.Utils.ResUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * Created by long on 2018-4-12.
 * 功能：Activity的基类
 */
public abstract class BasicAct extends AppCompatActivity {

    public Bundle savedInstanceState;
    public int contentResId;
    public int titleResId;
    public int rightResId;
    public boolean hasBackButton = true;
    public int toolbarType = TOOLBAR_TYPE_DEFAULT;
    public int systemBarColor;

    public Toolbar toolbar;
    public TextView textTitle;
    public TextView rightTitle;
    public RelativeLayout imageBackBtn;

    public static final int NO_CONTENT = 0;
    public static final int NO_TITLE = 0;
    public static final int TOOLBAR_TYPE_DEFAULT = 0;
    public static final int TOOLBAR_TYPE_FULL_SCREEN = 1;
    public static final int TOOLBAR_TYPE_NO_TOOLBAR = 2;
    public static final int TOOLBAR_TYPE_TITLE = 3;
    private RelativeLayout rightBtn;

    public BasicAct(int contentResId, int titleResId) {
        this(contentResId, titleResId, true, TOOLBAR_TYPE_DEFAULT);
    }

    public BasicAct(int contentResId, int titleResId, boolean hasBackButton) {
        this(contentResId, titleResId, hasBackButton, TOOLBAR_TYPE_DEFAULT);
    }

    public BasicAct(int contentResId, int titleResId, boolean hasBackButton, int toolbarType) {
        this(contentResId, titleResId, hasBackButton, toolbarType, R.color.colorPrimaryDark);
    }

    public BasicAct(int contentResId, int titleResId, boolean hasBackButton, int toolbarType, int
            systemBarColor) {
        this.contentResId = contentResId;
        this.titleResId = titleResId;
        this.hasBackButton = hasBackButton;
        this.toolbarType = toolbarType;
        this.systemBarColor = systemBarColor;
    }

    public BasicAct(int contentResId, int titleResId, int rightResId, boolean hasBackButton, int toolbarType, int
            systemBarColor) {
        this.contentResId = contentResId;
        this.titleResId = titleResId;
        this.rightResId = rightResId;
        this.hasBackButton = hasBackButton;
        this.toolbarType = toolbarType;
        this.systemBarColor = systemBarColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;

        hideBottomUIMenu();
        ActivityCollector.addActivity(this);
        initToolBar();
        initButterKnife();
        initView();
//        AndroidBug54971Workaround.assistActivity(findViewById(android.R.id.content));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    /**
     * 初始化布局文件
     */
    private void initContent() {
        if (NO_CONTENT != contentResId) {
            setContentView(contentResId);
        }
    }

    /**
     * 初始化状态的颜色
     */
    public void initSystemBar(int systemBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(BasicAct.this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        tintManager.setStatusBarTintResource(systemBarColor);

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);

    }


    /**
     * 初始化Toolbar
     */
    private void initToolBar() {
        switch (toolbarType) {
            case TOOLBAR_TYPE_FULL_SCREEN:
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                initContent();
                break;
            case TOOLBAR_TYPE_NO_TOOLBAR:
                initContent();
                initSystemBar(systemBarColor);
                break;
            case TOOLBAR_TYPE_TITLE:
                initContent();
                textTitle = findViewById(R.id.tool_bar_friends_circle_title);
                rightTitle = findViewById(R.id.tv_right);
                imageBackBtn = findViewById(R.id.tool_bar_friends_circle_btn_back);
                rightBtn = findViewById(R.id.tool_bar_friends_circle_right_text);

                if (hasBackButton) {
                    imageBackBtn.setVisibility(View.VISIBLE);
                    imageBackBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                } else {
                    if (imageBackBtn != null) {
                        imageBackBtn.setVisibility(View.GONE);
                    }
                }

                if (rightResId != NO_TITLE && rightTitle != null) {
                    rightBtn.setVisibility(View.VISIBLE);
                    rightTitle.setText(ResUtils.getString(rightResId));
                } else {
                    if (rightBtn != null) {
                        rightBtn.setVisibility(View.GONE);
                    }
                }

                if (titleResId != NO_TITLE && textTitle != null) {
                    if (titleResId == NO_TITLE) {
                        textTitle.setText(ResUtils.getString(R.string.default_toolbar_title));
                    } else {
                        textTitle.setText(ResUtils.getString(titleResId));
                    }
                }
                initSystemBar(systemBarColor);
                break;
            default:
                initContent();
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                textTitle = (TextView) toolbar.findViewById(R.id.tool_bar_title);
                imageBackBtn = (RelativeLayout) toolbar.findViewById(R.id.tool_bar_btn_back);

                if (hasBackButton) {
                    imageBackBtn.setVisibility(View.VISIBLE);
                    imageBackBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                } else {
                    if (imageBackBtn != null) {
                        imageBackBtn.setVisibility(View.GONE);
                    }
                }

                if (titleResId != NO_TITLE && textTitle != null) {
                    if (titleResId == NO_TITLE) {
                        textTitle.setText(ResUtils.getString(R.string.default_toolbar_title));
                    } else {
                        textTitle.setText(ResUtils.getString(titleResId));
                    }
                }
                initSystemBar(systemBarColor);

                break;
        }
    }

    /**
     * 初始化注解式框架
     */
    private void initButterKnife() {
        ButterKnife.bind(this);
    }

    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}