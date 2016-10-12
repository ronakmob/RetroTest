package com.rx.retro.viewModel.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.rx.retro.sample.R;
import com.rx.retro.viewModel.fragment.BaseFragment;

import java.util.List;


/**
 * Created by ronakmehta on 10/5/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public void setFragment(@NonNull final FragmentActivity mActivity, @NonNull Fragment mTargetFragment, boolean addToBackStack) {
        final FragmentTransaction fragmentTransaction = mActivity.getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(mTargetFragment.getTag());
        }
        fragmentTransaction.replace(R.id.container, mTargetFragment, mTargetFragment.getTag());
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void addFragment(@NonNull final FragmentActivity mActivity, @NonNull Fragment mTargetFragment, boolean addToBackStack) {
        final FragmentTransaction fragmentTransaction = mActivity.getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(mTargetFragment.getTag());
        }
        fragmentTransaction.add(R.id.container, mTargetFragment, mTargetFragment.getTag());
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void setRootFragment(@NonNull final FragmentActivity mActivity, @NonNull Fragment mTargetFragment) {
        clearBackStack(mActivity.getSupportFragmentManager());
        addFragment(mActivity, mTargetFragment, false);
    }

    public void restore(@NonNull final FragmentActivity mActivity, @NonNull Fragment mTargetFragment, FragmentManager.OnBackStackChangedListener onBackStackChangedListener, final boolean mWithStateLoss) {
        final FragmentManager manager = mActivity.getSupportFragmentManager();
        int backStackCount = manager.getBackStackEntryCount();
        Fragment restoredFragment = null;
        if (backStackCount > 0) {
            final String fragmentTag = manager.getBackStackEntryAt(backStackCount - 1).getName();
            restoredFragment = manager.findFragmentByTag(fragmentTag);
        } else {
            List<Fragment> fragmentList = manager.getFragments();
            if (fragmentList != null && fragmentList.size() == 1) {
                restoredFragment = fragmentList.get(0);
            }
        }
        if (restoredFragment == null) {
            restoredFragment = manager.findFragmentByTag(mTargetFragment.getTag());
        }
        if (restoredFragment == null) {
            setRootFragment(mActivity, mTargetFragment);
            return;
        } else {
            final FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.container, restoredFragment, restoredFragment.getClass().getSimpleName());
            if (mWithStateLoss) {
                fragmentTransaction.commitAllowingStateLoss();
            } else {
                fragmentTransaction.commit();
            }
        }
    }

    private void clearBackStack(@NonNull final FragmentManager manager) {
        while (manager.popBackStackImmediate()) {
            // nothing to do
        }
    }

    @Nullable
    public BaseFragment getCurrentFragment(FragmentActivity activity) {
        final FragmentManager manager = activity.getSupportFragmentManager();
        int backStackCount = manager.getBackStackEntryCount();
        Fragment restoredFragment = null;
        if (backStackCount > 0) {
            final String fragmentTag = manager.getBackStackEntryAt(backStackCount - 1).getName();
            restoredFragment = manager.findFragmentByTag(fragmentTag);
        }
        List<Fragment> fragmentList = manager.getFragments();
        if (fragmentList != null && fragmentList.size() > 0) {
            restoredFragment = fragmentList.get(0);
        }
        return (BaseFragment) restoredFragment;
    }
}
