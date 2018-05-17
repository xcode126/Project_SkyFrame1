package com.sky.skyframe1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.sky.skyframe1.R;
import com.sky.skyframe1.widget.TabRadioGroup;

import java.util.List;

/**
 * Author：sky on 2018/2/23 10:40
 * Email：xcode126@126.com
 * main-TabAdapter
 */

public class FragmentTabAdapter implements TabRadioGroup.OnCheckedChangeListener {
    /**
     * Used to construct the parameters passed in by a function.
     */
    private FragmentActivity fragmentActivity;
    private List<Fragment> fragments;
    private int fragmentContentId;
    private TabRadioGroup rgs;

    private int currentTab; //The current Tab page index.
    // Used to make the caller add new functionality when switching tabs.
    private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener;


    /**
     * construct
     *
     * @param fragmentActivity  The Activity that Fragment belongs to.
     * @param fragments         A TAB page corresponds to a Fragment.
     * @param fragmentContentId The id of the region to be replaced in the Activity.
     * @param rgs               Used to switch tabs
     */
    public FragmentTabAdapter(FragmentActivity fragmentActivity,
                              List<Fragment> fragments, int fragmentContentId, TabRadioGroup rgs) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;
        // 默认显示第一页
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(0));
        ft.commitAllowingStateLoss();//commit
        rgs.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(TabRadioGroup radioGroup, int checkedId) {
        radioGroup.setCheckWithoutNotif(checkedId);
//        if (checkedId == R.id.rb_person) {
//           check is login
//            if (StringUtils.isEmpty(ConfigUtil.getInstate().getUserModel().getUser_token())) {
//            if (ConfigUtil.getInstate().getUserModel() == null) {
//                fragmentActivity.startActivity(new Intent(fragmentActivity,
//                        LoginActivity.class));
//                return;
//            }
//        }
//       }
        for (int i = 0; i < rgs.getChildCount(); i++) {
//			if (rgs.getChildAt(i).getId() == R.id.rb_grab) {
//				continue;
//			}
            getRadioButton(rgs.getChildAt(i), checkedId, radioGroup, i);
            // getRadioButton(rgs.getChildAt(i), checkedId, radioGroup,
            // (i >= rgs.getChildCount() - 1 ? i - 1 : i));
        }
    }

    private int checkedIds = -1;

    /**
     * get all radio buttons which are in the view
     *
     * @param child
     * @param checkedId
     * @param radioGroup
     * @param j
     */
    private void getRadioButton(View child, int checkedId, TabRadioGroup radioGroup, int j) {
        if (child instanceof RadioButton) {
            // If you set up the TAB additional functional interface.
            if (checkedId == child.getId()) {
                if (checkedIds != checkedId) {
                    checkedIds = checkedId;
                    if (null != onRgsExtraCheckedChangedListener) {
                        onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(radioGroup, checkedId, j);
                    }
                    Fragment fragment = fragments.get(j);
                    FragmentTransaction ft = obtainFragmentTransaction(j);
                    getCurrentFragment().onPause(); // pause current tab
                    if (fragment.isAdded()) {
                        fragment.onResume(); // Start the target TAB of onResume()
                        ft.show(fragment);
                    } else {
                        ft.add(fragmentContentId, fragment, String.valueOf(j)).show(fragment);
                    }
                    // showTab(j); // show the target tab
                    ft.commitAllowingStateLoss();//commit
                    currentTab = j; // Update the target TAB for the current TAB.
                }
            } else {
                Fragment fragment = fragments.get(j);
                if (fragment.isAdded()) {
                    FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
                    ft.hide(fragment).commitAllowingStateLoss(); // Hides the current fragment and displays the next one.//commit
                }
            }
        } else if (child instanceof ViewGroup) {
            int counts = ((ViewGroup) child).getChildCount();
            for (int i = 0; i < counts; i++) {
                getRadioButton(((ViewGroup) child).getChildAt(i), checkedId, radioGroup, j);
            }
        }
    }

    /**
     * Get the FragmentTransaction that drives the painting.
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager()
                .beginTransaction();
        // Set the switch animation
        if (index > currentTab) {
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        } else {
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        }
        return ft;
    }

    /**
     * Get the current currentTab.
     *
     * @return
     */
    public int getCurrentTab() {
        return currentTab;
    }

    /**
     * Gets the current Fragment.
     *
     * @return
     */
    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    /**
     * Toggle TAB additional functional interface.
     */
    public static class OnRgsExtraCheckedChangedListener {
        public void OnRgsExtraCheckedChanged(TabRadioGroup radioGroup, int checkedId, int index) {
        }
    }

    public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
        return onRgsExtraCheckedChangedListener;
    }

    public void setOnRgsExtraCheckedChangedListener(OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
        this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
    }
}
