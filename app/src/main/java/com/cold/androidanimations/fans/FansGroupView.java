package com.cold.androidanimations.fans;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cold.androidanimations.R;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.AlphaModifier;

public class FansGroupView extends RelativeLayout{

    private Context mContext;

    /**
     * 阴影
     */
    private FrameLayout flytShadow;

    /**
     * 光
     */
    private ImageView imgvLight;

    /**
     * 粒子
     */
    private LinearLayout llytParticle;

    /**
     * 粉丝团
     */
    private ImageView imgvFans;

    /**
     * 关闭
     */
    private ImageView imgvClose;

    /**
     * 提示
     */
    private TextView tvTip;

    /**
     * 任务按钮
     */
    private TextView tvTask;

    /**
     * 动画周期，延长时间用来测试
     */
    private int duration = 2000;


    /************************************************************************************************
     *
     * construct
     *
     ***********************************************************************************************/
    public FansGroupView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public FansGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public FansGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init(){
        initView();
    }

    private void initView() {
        if(mContext != null) {
            inflate(mContext, R.layout.item_fans_group_view, this);
            flytShadow = (FrameLayout) findViewById(R.id.flyt_shadow);
            imgvLight = (ImageView)findViewById(R.id.imgv_light);
            llytParticle = (LinearLayout) findViewById(R.id.llyt_particle);
            imgvFans = (ImageView)findViewById(R.id.imgv_fans);
            imgvClose = (ImageView)findViewById(R.id.imgv_close);
            tvTip = (TextView) findViewById(R.id.tv_tip);
            tvTask = (TextView)findViewById(R.id.tv_task);
//            llytParticle.post(new Runnable() {
//                @Override
//                public void run() {
//                    startAnim();
//                }
//            });
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    startAnim();
                }
            }, 2000);

        }
    }

    private void startAnim1() {
        new ParticleSystem(llytParticle, 1000, ContextCompat.getDrawable(mContext, R.drawable.white_start), 3000)
                .addModifier(new AlphaModifier(255, 75, 0, 2000)) // 透明度范围
                .setSpeedModuleAndAngleRange(0.05f, 0.3f, 0, 360) // 速度范围，角度范围
                .setRotationSpeedRange(0, 90) // 旋转角度范围
                .setScaleRange(0.6f, 1.2f) // 缩放范围
                .setAcceleration(0, 90)
                .emit(llytParticle, 12);
    }

    /**
     * 启动动画
     */
    private void startAnim() {
        initState();
        // 徽章
        // 平移
        ObjectAnimator animY1 = ObjectAnimator.ofFloat(imgvFans,"translationY",321, -324).setDuration(duration / 10 * 3); // 从当前位置往下107处上移215dp
        animY1.addListener(new SimpleAnimatorListener() {

            @Override
            public void onAnimationEnd(Animator animation) {
                if(llytParticle != null && mContext != null) { // 预防控件还没layout的情况
                    llytParticle.post(new Runnable() {
                        @Override
                        public void run() {
                            new ParticleSystem(llytParticle, 1000, ContextCompat.getDrawable(mContext, R.drawable.ic_particle), 3000)
                                    .addModifier(new AlphaModifier(255, 150, 0, 2000)) // 透明度范围
                                    .setSpeedModuleAndAngleRange(0.01f, 0.1f, 0, 360) // 速度范围，角度范围
                                    .setRotationSpeedRange(0, 90) // 旋转角度范围
                                    .setScaleRange(0.6f, 2.2f) // 缩放范围
                                    .emit(llytParticle, 12);
                        }
                    });
                }
            }
        });
        animY1.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator animY2 = ObjectAnimator.ofFloat(imgvFans,"translationY",-324, 0).setDuration(duration / 10 * 2); // 下移108dp

        animY2.setInterpolator(new AccelerateInterpolator());

        // 缩放
        ObjectAnimator animSx1 = ObjectAnimator.ofFloat(imgvFans,"scaleX",0, 0.5f).setDuration(duration / 10 * 3); //
        animSx1.setInterpolator(new DecelerateInterpolator(2));
        ObjectAnimator animSy1 = ObjectAnimator.ofFloat(imgvFans,"scaleY",0,0.5f).setDuration(duration / 10 * 3); //
        animSy1.setInterpolator(new DecelerateInterpolator(2));


        ObjectAnimator animSx2 = ObjectAnimator.ofFloat(imgvFans,"scaleX",0.5f, 1.0f).setDuration(duration / 10 * 2); //
        animSx2.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator animSy2 = ObjectAnimator.ofFloat(imgvFans,"scaleY",0.5f, 1.0f).setDuration(duration / 10 * 2); //
        animSy2.setInterpolator(new DecelerateInterpolator());

        // Y轴旋转
        ObjectAnimator animR1 = ObjectAnimator.ofFloat(imgvFans,"rotationY",0, 1800).setDuration(duration / 10 * 3);
        animR1.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator animR2 = ObjectAnimator.ofFloat(imgvFans,"rotationY",0, 360).setDuration(duration / 10 * 7);
        animR2.setInterpolator(new DecelerateInterpolator(3));

        // 光
        ObjectAnimator animLightR = ObjectAnimator.ofFloat(imgvLight,"rotation",0,360).setDuration(4800); // 绕X轴翻转
        animLightR.setRepeatCount(ValueAnimator.INFINITE);
        animLightR.setRepeatMode(ValueAnimator.RESTART);
        animLightR.setInterpolator(new LinearInterpolator());
        ObjectAnimator animLightA = ObjectAnimator.ofFloat(imgvLight,"alpha",0, 1).setDuration(duration / 10 * 1); // 透明度
        animLightA.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                imgvLight.setVisibility(View.VISIBLE);
            }
        });

        // 其他内容
        ObjectAnimator animCloseA = ObjectAnimator.ofFloat(imgvClose,"alpha",0, 1).setDuration(duration / 10 * 1); // 透明度
        animCloseA.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                imgvClose.setVisibility(View.VISIBLE);
                tvTip.setVisibility(View.VISIBLE);
                tvTask.setVisibility(View.VISIBLE);
            }
        });
        ObjectAnimator animTipA = ObjectAnimator.ofFloat(tvTip,"alpha",0, 1).setDuration(duration / 10 * 1); // 透明度
        ObjectAnimator animTaskA = ObjectAnimator.ofFloat(tvTask,"alpha",0, 1).setDuration(duration / 10 * 1); // 透明度

        // 阴影
        ObjectAnimator animShadowA = ObjectAnimator.ofFloat(flytShadow,"alpha",0, 0.8f).setDuration(duration / 10 * 1);
        animShadowA.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                flytShadow.setVisibility(View.VISIBLE);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animSx1, animSy1, animShadowA);
        set.play(animR2).after(animR1);
        set.play(animY1);
        set.play(animY2).after(animR1);
        set.play(animSx2).after(animY1);
        set.play(animSy2).after(animY1);
        set.play(animLightR).after(animY2);
        set.play(animLightA).after(animY2);
        set.play(animCloseA).after(animY2);
        set.play(animTipA).after(animY2);
        set.play(animTaskA).after(animY2);
        set.start();

    }

    /**
     * 初始化状态
     */
    private void initState() {
        imgvLight.setVisibility(View.GONE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }

    class SimpleAnimatorListener implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
