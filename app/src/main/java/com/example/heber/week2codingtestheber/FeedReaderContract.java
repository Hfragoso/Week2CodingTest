package com.example.heber.week2codingtestheber;

import android.provider.BaseColumns;

/**
 * Created by heber on 7/28/2017.
 */

public final class FeedReaderContract {
    private FeedReaderContract() {

    }

    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NOTE_TITLE = "noteTitle";
        public static final String COLUMN_NAME_NOTE_CONTENT = "noteContent";
    }
}
