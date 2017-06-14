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

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.appliberated.penaltyflip.features.list.PenaltyCardListAdapter;
import com.appliberated.penaltyflip.features.list.PenaltyCardListItem;
import com.appliberated.penaltyflip.utils.SystemUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * The main activity with the penalty card list.
 */
public class MainActivity extends ListActivity {

    /**
     * The random number generator used to generate random penalty cards.
     */
    private Random mRandom;

    /**
     * The max brightness mode.
     */
    private boolean mMaxCardBrightness = false;

    /**
     * The max brightness menu item.
     */
    private MenuItem mMaxCardBrightnessMenuItem;

    /**
     * Perform required activity initialization when the activity is starting.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add the header view
        ListView listView = getListView();
        TextView headerTextView = (TextView) getLayoutInflater().inflate(R.layout.penalty_card_list_header, listView, false);
        listView.addHeaderView(headerTextView, null, false);

        // Init the penalty card list adapter
        ArrayList<PenaltyCardListItem> itemsArray = new ArrayList<>();
        PenaltyCardListAdapter adapter = new PenaltyCardListAdapter(this, itemsArray);
        setListAdapter(adapter);

        // Add the built-in penalty cards to the list
        adapter.add(new PenaltyCardListItem(this, R.string.red_card_name, R.string.red_card_description, Color.RED));
        adapter.add(new PenaltyCardListItem(this, R.string.yellow_card_name, R.string.yellow_card_description, Color.YELLOW));
        adapter.add(new PenaltyCardListItem(this, R.string.green_card_name, R.string.green_card_description, Color.GREEN));
        adapter.add(new PenaltyCardListItem(this, R.string.white_card_name, R.string.white_card_description, Color.WHITE));
        adapter.add(new PenaltyCardListItem(this, R.string.blue_card_name, R.string.blue_card_description, Color.BLUE));
        adapter.add(new PenaltyCardListItem(this, R.string.black_card_name, R.string.black_card_description, Color.BLACK));

        // Init the random number generator
        mRandom = new Random();
    }

    /**
     * Inflates the menu items for use in the action bar.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        mMaxCardBrightnessMenuItem = menu.findItem(R.id.action_max_brightness);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Load preferences when the activity is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();
    }

    /**
     * Save preferences when the activity is paused.
     */
    @Override
    protected void onPause() {
        super.onPause();
        savePreferences();
    }

    // ========================================================================
    // Main UI Events
    // ========================================================================

    /**
     * Show the corresponding penalty card when a penalty card list item is clicked.
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        showPenaltyCard((int) v.getTag());
    }

    // ========================================================================
    // Action Bar Events
    // ========================================================================

    /**
     * Action Bar -> Random penalty card: Show a penalty card of a random color.
     */
    @SuppressWarnings("UnusedParameters")
    public void actionRandomCard(MenuItem item) {
        int cardIndex = mRandom.nextInt(getListAdapter().getCount());
        int cardColor = ((PenaltyCardListItem) getListAdapter().getItem(cardIndex)).getColor();
        showPenaltyCard(cardColor);
    }

    /**
     * "Max brightness" menu item: toggle screen full brightness.
     */
    public void actionMaxBrightness(MenuItem item) {
        mMaxCardBrightness = !mMaxCardBrightness;
        item.setChecked(mMaxCardBrightness);
    }

    /**
     * Action Bar -> Wikipedia article: Open the "Penalty card" Wikipedia article
     */
    @SuppressWarnings("UnusedParameters")
    public void actionWikipediaArticle(MenuItem item) {
        SystemUtils.viewUrl(this, getString(R.string.action_wikipedia_article_url));
    }

    /**
     * Action Bar -> Rate app: Open the app Google Play page to allow the user to rate the app.
     */
    @SuppressWarnings("UnusedParameters")
    public void actionRateApp(MenuItem item) {
        SystemUtils.viewUrl(this, getString(R.string.action_rate_url));
    }

    /**
     * Action Bar -> Help: Open the app home page.
     */
    @SuppressWarnings("UnusedParameters")
    public void actionHelp(MenuItem item) {
        SystemUtils.viewUrl(this, getString(R.string.action_help_url));
    }

    // ========================================================================
    // Preferences
    // ========================================================================

    /**
     * Loads the preferences used by this activity.
     */
    private void loadPreferences() {
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        // Load the max card brightness setting and update the menu item
        mMaxCardBrightness = sharedPref.getBoolean(getString(R.string.pref_max_card_brightness), false);
        if (mMaxCardBrightnessMenuItem != null) {
            mMaxCardBrightnessMenuItem.setChecked(mMaxCardBrightness);
        }
    }

    /**
     * Saves the preferences used by this activity.
     */
    private void savePreferences() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().
                putBoolean(getString(R.string.pref_max_card_brightness), mMaxCardBrightness).apply();
    }

    // ========================================================================
    // Private Functionality Methods
    // ========================================================================

    /**
     * Starts the PenaltyCardActivity to show a fullscreen penalty card.
     */
    private void showPenaltyCard(int color) {
        Intent intent = new Intent(this, PenaltyCardActivity.class);
        intent.putExtra(PenaltyCardActivity.EXTRA_COLOR, color);
        intent.putExtra(PenaltyCardActivity.EXTRA_MAX_BRIGHTNESS, mMaxCardBrightness);
        startActivity(intent);
    }
}
