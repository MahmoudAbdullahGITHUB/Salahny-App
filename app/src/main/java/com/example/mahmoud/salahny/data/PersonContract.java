package com.example.mahmoud.salahny.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class PersonContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public PersonContract() {}


    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.salahny";


    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_PERSONS = "persons";



    public static final class PersonEntry implements BaseColumns {

        /** The content URI to access the pet data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PERSONS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PERSONS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PERSONS;


        public final static String TABLE_NAME = "Persons";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_PERSON_NAME ="name";

        public final static String COLUMN_PERSON_MAIL = "mail";

        public final static String COLUMN_PERSON_PASSWORD = "password";
/*
        public final static String COLUMN_PERSON_REGION = "region";

        public final static String COLUMN_PERSON_PHONE = "phone";

        public final static String COLUMN_PERSON_ADDRESS = "address";
        public final static String COLUMN_PERSON_GENDER = "gender";
*/


        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

        public static boolean isValidGender(int gender) {
            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
                return true;
            }
            return false;
        }
    }


    public static final class PostEntry implements BaseColumns {


        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PERSONS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PERSONS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PERSONS;


        public final static String TABLE_NAME = "Posts";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_POST_OWNER_ID = "person_Id";

        public final static String COLUMN_POST_IMAGE ="image_name";

        public final static String COLUMN_POST_MODEL ="model";

        public final static String COLUMN_POST_DESCRIPTION = "description";

        public final static String COLUMN_POST_REGION = "region";

        public final static String COLUMN_POST_DATE = "date";

        public final static boolean Available = false;


    }




}












/**
 * Inner class that defines constant values for the pets database table.
 * Each entry in the table represents a single pet.
 */





/** Name of database table for pets */

/**
 * Unique ID number for the pet (only for use in the database table).
 *
 * Type: INTEGER
 */


/**
 * Name of the pet.
 *
 * Type: TEXT
 */

/**
 *   mail >>>   TEXT
 */

/**
 * Password >>>  TEXT
 */

/**
 * Region >>>  TEXT
 */


/**
 * Phone >>> TEXT
 */

/**
 * Adress >>>  TEXT
 */


/**
 * Gender of the pet.
 *
 * The only possible values are {@link #GENDER_UNKNOWN}, {@link #GENDER_MALE},
 * or {@link #GENDER_FEMALE}.
 *
 * Type: INTEGER
 */

/**
 * Possible values for the gender of the pet.
 */

/**
 * Returns whether or not the given gender is {@link #GENDER_UNKNOWN}, {@link #GENDER_MALE},
 * or {@link #GENDER_FEMALE}.
 */