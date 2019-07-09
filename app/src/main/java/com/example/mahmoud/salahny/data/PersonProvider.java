package com.example.mahmoud.salahny.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.mahmoud.salahny.data.PersonContract.PersonEntry;

public class PersonProvider extends ContentProvider {

    /** Tag for the log messages */
    public static final String LOG_TAG = PersonProvider.class.getSimpleName();

    /** URI matcher code for the content URI for the pets table */
    private static final int PERSONS = 100;

    /** URI matcher code for the content URI for a single pet in the pets table */
    private static final int PERSON_ID = 101;


    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI of the form "content://com.example.android.pets/pets" will map to the
        // integer code {@link #PETS}. This URI is used to provide access to MULTIPLE rows
        // of the pets table.
        sUriMatcher.addURI(PersonContract.CONTENT_AUTHORITY, PersonContract.PATH_PERSONS, PERSONS);

        // The content URI of the form "content://com.example.android.pets/pets/#" will map to the
        // integer code {@link #PET_ID}. This URI is used to provide access to ONE single row
        // of the pets table.
        //
        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
        // For example, "content://com.example.android.pets/pets/3" matches, but
        // "content://com.example.android.pets/pets" (without a number at the end) doesn't match.
        sUriMatcher.addURI(PersonContract.CONTENT_AUTHORITY, PersonContract.PATH_PERSONS + "/#", PERSON_ID);
    }


    public PersonDbHelper mDbHelper ;


    @Override
    public boolean onCreate() {
        mDbHelper = new PersonDbHelper(getContext());
        return true;
    }

    //@Nullable    // what this mean ???
    @Override
    public Cursor query( Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSONS:

                cursor = database.query(PersonEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case PERSON_ID:

                selection = PersonEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };


                cursor = database.query(PersonEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }



    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSONS:
                return insertPet(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }


    private Uri insertPet(Uri uri, ContentValues values) {
        // Check that the name is not null
        String name = values.getAsString(PersonEntry.COLUMN_PERSON_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Person requires a name");
        }

        /*
        // Check that the gender is valid
        Integer gender = values.getAsInteger(PersonEntry.COLUMN_PERSON_GENDER);
        if (gender == null || !PersonEntry.isValidGender(gender)) {
            throw new IllegalArgumentException("Person requires valid gender");
        }

        String region = values.getAsString(PersonEntry.COLUMN_PERSON_REGION);
        if (region==null){
            throw new IllegalArgumentException("Person requires a region");
        }

        String phone = values.getAsString(PersonEntry.COLUMN_PERSON_PHONE);
        if (phone == null ) {
            throw new IllegalArgumentException("Person requires valid phone");
        }
*/



        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(PersonEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }






    //@Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}
