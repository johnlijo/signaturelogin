package com.lijo.signaturelogin;

import android.provider.BaseColumns;

public final class UserContents {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserContents() {}

    /* Inner class that defines the table contents */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_USER= "username";
        public static final String COLUMN_PASSWORD = "paassword";
    }

}
