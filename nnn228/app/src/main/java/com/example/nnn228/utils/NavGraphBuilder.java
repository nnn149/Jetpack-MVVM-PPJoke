package com.example.nnn228.utils;

import android.content.ComponentName;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

import com.example.libcommon.AppGlobals;
import com.example.nnn228.FixFragmentNavigator;
import com.example.nnn228.data.bean.Destination;

import java.util.HashMap;

public class NavGraphBuilder {
    public static void build(NavController controller, FragmentActivity activity,int containerId) {
        NavigatorProvider provider = controller.getNavigatorProvider();

        //FragmentNavigator fragmentNavigator = provider.getNavigator(FragmentNavigator.class);
        // 修复版本show hide Fragment
        FixFragmentNavigator fixFragmentNavigator = new FixFragmentNavigator(activity, activity.getSupportFragmentManager(), containerId);
        provider.addNavigator(fixFragmentNavigator);
        ActivityNavigator activityNavigator = provider.getNavigator(ActivityNavigator.class);
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));
        HashMap<String, Destination> destConfig = AppConfig.getDestConfig();
        for (Destination value : destConfig.values()) {
            if (value.isFragment) {
                FragmentNavigator.Destination destination = fixFragmentNavigator.createDestination();
                destination.setClassName(value.className);
                destination.setId(value.id);
                destination.addDeepLink(value.pageUrl);
                navGraph.addDestination(destination);
            } else {
                ActivityNavigator.Destination destination = activityNavigator.createDestination();
                destination.setId(value.id);
                destination.addDeepLink(value.pageUrl);
                destination.setComponentName(new ComponentName(AppGlobals.getApplication().getPackageName(), value.className));
                navGraph.addDestination(destination);
            }
            if (value.asStarter) {
                navGraph.setStartDestination(value.id);
            }
        }
        controller.setGraph(navGraph);
    }
}
