package com.example.user.sqlite.DB;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

public class PersonProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DataBaseHelper mOpenHelper;

    private static final int PERSON = 100;
    private static final int PERSON_ITEM = 200;

    private static final SQLiteQueryBuilder sStoreBuilder;

    static{
        sStoreBuilder = new SQLiteQueryBuilder();
    }

    private static UriMatcher buildUriMatcher() {

        final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ContentContract.CONTENT_AUTHORITY;

        sURIMatcher.addURI(authority, ContentContract.PATH_PERSON, PERSON);
        sURIMatcher.addURI(authority, ContentContract.PATH_PERSON + "/*", PERSON_ITEM);

        return sURIMatcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new DataBaseHelper(getContext());
        return true;
    }

    @Override
    public String getType(@NonNull Uri uri) {

        final int match = sUriMatcher.match(uri);

        switch (match) {
            case PERSON:
                return ContentContract.PersonEntry.CONTENT_TYPE;
            case PERSON_ITEM:
                return ContentContract.PersonEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case PERSON: {
                retCursor = mOpenHelper.getReadableDatabase()
                        .query(ContentContract.PersonEntry.TABLE_PERSON,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                break;
            }
            case PERSON_ITEM: {
                retCursor = mOpenHelper.getReadableDatabase()
                        .query(ContentContract.PersonEntry.TABLE_PERSON,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case PERSON: {
                long _id = db.insert(ContentContract.PersonEntry.TABLE_PERSON, null, values);
                if ( _id > 0 )
                    returnUri = ContentContract.PersonEntry.buildMovieUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if (selection == null) { selection = "1"; }

        switch (match) {
            case PERSON: {
                rowsDeleted = db.delete(ContentContract.PersonEntry.TABLE_PERSON, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(
            @NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case PERSON: {
                rowsUpdated = db.update(ContentContract.PersonEntry.TABLE_PERSON, values, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;

    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSON:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ContentContract.PersonEntry.TABLE_PERSON, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    @TargetApi(11)
    public void shutdown() {
        mOpenHelper.close();
        super.shutdown();
    }
}
