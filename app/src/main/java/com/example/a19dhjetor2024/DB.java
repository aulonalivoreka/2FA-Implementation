package com.example.a19dhjetor2024;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

public class DB extends SQLiteOpenHelper {
    private static final String DBNAME = "db";

    public DB(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT, " +
                "fullName TEXT, " +
                "passwordHash TEXT, " +
                "salt TEXT, " +
                "verificationCode TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean signUp(String name, String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);
        String verificationCode = generateVerificationCode();

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("fullName", name);
        contentValues.put("passwordHash", hashedPassword);
        contentValues.put("salt", salt);
        contentValues.put("verificationCode", verificationCode);

        long result = db.insert("User", null, contentValues);
        db.close();

        if (result != -1) {
            EmailSender emailSender = new EmailSender();
            try {
                emailSender.sendOTPEmail(email, "Verification Code", "Verification Code for Sign up: " + verificationCode);
                Log.d("DB", "Inserting user with email: " + email);
            } catch (Exception e) {
                Log.e("EmailError", "Failed to send OTP email", e);
            }
            return true;
        }
        return false;
    }

    public String generateVerificationCode() {
        Random random = new Random();
        return Integer.toString(random.nextInt(999999));
    }

    public String getVerificationCode(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String verificationCode = null;

        Cursor cursor = db.rawQuery(
                "SELECT verificationCode FROM User WHERE email = ?",
                new String[]{email}
        );

        if (cursor != null && cursor.moveToFirst()) {
            verificationCode = cursor.getString(cursor.getColumnIndexOrThrow("verificationCode"));
        }

        if (cursor != null) {
            cursor.close();
        }
        return verificationCode;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE email=? and verificationCode = ?", new String[]{email, "0"});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void validateTheUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL("UPDATE User SET verificationCode = ? WHERE email=?", new Object[]{"0", email});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("DBError", "Error updating user verification", e);
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public void logInUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String verificationCode = generateVerificationCode();

        db.execSQL("UPDATE User SET verificationCode = ? WHERE email=?", new Object[]{verificationCode, email});
        EmailSender emailSender = new EmailSender();
        emailSender.sendOTPEmail(email, "Verification Code for Log in", verificationCode);
    }

    public boolean validateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT passwordHash, salt FROM User WHERE email=? and verificationCode = ?", new String[]{email, "0"});

        if (cursor.moveToFirst()) {
            String storedHash = cursor.getString(0);
            cursor.close();
            return BCrypt.checkpw(password, storedHash);
        }
        cursor.close();
        return false;
    }
}
