/*
 * Copyright (C) 2014-2017 Appliberated
 * http://www.appliberated.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appliberated.penaltyflip.utils;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * A set of user interface utility methods.
 */
public class UiUtils {

    /**
     * Sets the full screen immersive sticky mode.
     */
    public static void setImmersiveFullScreen(Window window) {
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /**
     * Sets the Crossfade rotation animation.
     */
    public static void setCrossFadeRotationAnimation(Window window) {
        WindowManager.LayoutParams winParams = window.getAttributes();
        winParams.rotationAnimation = WindowManager.LayoutParams.ROTATION_ANIMATION_CROSSFADE;
        window.setAttributes(winParams);
    }

    /**
     * Sets maximum (full) screen brightness policy.
     */
    public static void setMaxBrightness(Window window) {
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL;
        window.setAttributes(lp);
    }
}
