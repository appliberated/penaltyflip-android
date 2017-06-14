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

package com.appliberated.penaltyflip.features.list;

import android.content.Context;

/**
 * The penalty card list item class.
 */
public class PenaltyCardListItem {
    /**
     * The penalty card name.
     */
    private final String mName;

    /**
     * The penalty card description.
     */
    private final String mDescription;

    /**
     * The penalty card color.
     */
    private final int mColor;

    /**
     * Create a new {@link PenaltyCardListItem}.
     */
    private PenaltyCardListItem(String name, String description, int color) {
        mName = name;
        mDescription = description;
        mColor = color;
    }

    /**
     * Create a new {@link PenaltyCardListItem}.
     */
    public PenaltyCardListItem(Context context, int nameResId, int descriptionResId, int color) {
        this(context.getString(nameResId), context.getString(descriptionResId), color);
    }

    /**
     * Get the name of this item.
     */
    String getName() {
        return mName;
    }

    /**
     * Get the description of this item.
     */
    String getDescription() {
        return mDescription;
    }

    /**
     * Get the color of this item.
     */
    public int getColor() {
        return mColor;
    }
}
