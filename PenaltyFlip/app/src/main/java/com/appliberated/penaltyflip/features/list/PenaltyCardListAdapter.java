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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appliberated.penaltyflip.R;

import java.util.ArrayList;

/**
 * The penalty card list adapter class.
 */
public class PenaltyCardListAdapter extends ArrayAdapter<PenaltyCardListItem> {

    /**
     * Construct an penalty card list adapter.
     */
    public PenaltyCardListAdapter(Context context, ArrayList<PenaltyCardListItem> items) {
        super(context, 0, items);
    }

    /**
     * Get a View that displays the penalty card data at the specified position in the data set.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        PenaltyCardListItem item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_penalty_card_list, parent, false);
        }

        if (item != null) {
            // Update penalty card name
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name_text_view);
            nameTextView.setText(item.getName());

            // Update penalty card description
            TextView descriptionTextView = (TextView) convertView.findViewById(R.id.description_text_view);
            descriptionTextView.setText(item.getDescription());

            // Update penalty card color
            View colorView = convertView.findViewById(R.id.color_view);
            int color = item.getColor();
            colorView.setBackgroundColor(color);
            convertView.setTag(color);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}