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
 *
 * This file incorporates work from:
 * https://gist.github.com/nesquena/ed58f34791da00da9751
 */

package com.appliberated.penaltyflip.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * A helper class that makes handling swipes as easy as possible.
 */
public class OnSwipeTouchListener implements View.OnTouchListener {

    /**
     * The gesture detector used to detect swipes.
     */
    private final GestureDetector mGestureDetector;

    /**
     * Creates a OnSwipeTouchListener.
     */
    protected OnSwipeTouchListener(Context c) {
        mGestureDetector = new GestureDetector(c, new GestureListener());
    }

    /**
     * Connect the swipe gesture detector to analyze the given motion event.
     */
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return mGestureDetector.onTouchEvent(motionEvent);
    }

    /**
     * Called when a swipe is detected.
     */
    public void onSwipe(Direction direction) {
    }

    /**
     * Physical directions
     */
    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * A gesture listener that knows swipe gestures.
     */
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        /**
         * The swipe physical distance threshold.
         */
        private static final int SWIPE_THRESHOLD = 100;

        /**
         * The swipe velocity threshold.
         */
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        /**
         * Return true from onDown to signal that we want to handle this motion event.
         */
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        /**
         * Determines the fling velocity and then fires the swipe event with the appropriate direction.
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    onSwipe(diffX > 0 ? Direction.RIGHT : Direction.LEFT);
                    return true;
                }
            } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                onSwipe(diffY > 0 ? Direction.DOWN : Direction.UP);
                return true;
            }

            return false;
        }
    }
}