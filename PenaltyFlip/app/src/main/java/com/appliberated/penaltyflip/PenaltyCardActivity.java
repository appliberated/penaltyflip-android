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

package com.appliberated.penaltyflip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.appliberated.penaltyflip.utils.OnSwipeTouchListener;
import com.appliberated.penaltyflip.utils.UiUtils;

/**
 * The fullscreen penalty card activity.
 */
public class PenaltyCardActivity extends Activity {

    private static boolean sShowTip = true;

    /**
     * Intent extra for passing integer color code values.
     */
    public static final String EXTRA_COLOR = "com.appliberated.penaltyflip.extra.COLOR";

    /**
     * Intent extra for passing the max brightness setting.
     */
    public static final String EXTRA_MAX_BRIGHTNESS = "com.appliberated.penaltyflip.extra.MAX_BRIGHTNESS";

    /**
     * Called when the activity is starting. Setup the views and events, and load app preferences.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalty_card);

        View cardView = findViewById(R.id.card_view);

        // Get the color of the penalty card, and fill the fullscreen root view with it
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_COLOR)) {
            int color = intent.getIntExtra(EXTRA_COLOR, 0);
            cardView.setBackgroundColor(color);
        }

        // Get the max brightness setting and apply it if true
        if (intent.hasExtra(EXTRA_MAX_BRIGHTNESS)) {
            boolean maxBrightness = intent.getBooleanExtra(EXTRA_MAX_BRIGHTNESS, false);
            if (maxBrightness) UiUtils.setMaxBrightness(getWindow());
        }

        // Go full screen immersive sticky
        UiUtils.setCrossFadeRotationAnimation(getWindow());
        UiUtils.setImmersiveFullScreen(getWindow());

        // Attach the swipe touch listener to finish the activity on left or right swipes
        cardView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipe(Direction direction) {
                if (direction == Direction.LEFT || direction == Direction.RIGHT) finish();
            }
        });

        // Show "swipe to close" tip the first time a card is shown in the current session
        if (sShowTip) {
            Toast.makeText(getApplicationContext(), R.string.penalty_card_swipe_to_close_tip, Toast.LENGTH_LONG).show();
            sShowTip = false;
        }
    }

    /**
     * Set immersive full screen mode when window gets focus.
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            UiUtils.setImmersiveFullScreen(getWindow());
        }
    }
}