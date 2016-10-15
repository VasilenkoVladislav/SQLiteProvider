package com.example.user.sqlite.DB;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContentContract {

    public static final String CONTENT_AUTHORITY = "com.example.user.sqlite";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PERSON = "persons";

    public static final class PersonEntry implements BaseColumns {

        public static final String TABLE_PERSON = "Person";

        public static final String _ID = "_id";

        public static final String COLUMN_PERSON_ID      = "PersonId";
        public static final String COLUMN_PERSON_NAME    = "Name";
        public static final String COLUMN_PERSON_SURNAME = "SurName";
        public static final String COLUMN_PERSON_AGE     = "Age";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PERSON).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PERSON;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PERSON;

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMovieTitle(String title) {
            return CONTENT_URI.buildUpon().appendPath(title).build();
        }
    }
}
