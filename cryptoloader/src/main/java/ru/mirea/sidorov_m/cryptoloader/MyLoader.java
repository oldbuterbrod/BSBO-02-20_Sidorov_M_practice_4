package ru.mirea.sidorov_m.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {
    byte[] cryptText;
    byte[] key;
    SecretKey originalKey;
    public static final String ARG_WORD = "Sidorov";
    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
        {
            this.cryptText = args.getByteArray(ARG_WORD);
            this.key = args.getByteArray("key");
            this.originalKey = new SecretKeySpec(this.key, 0, this.key.length, "AES");
        }
    }
    public String decryptMsg(){
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, this.originalKey);
            return new String(cipher.doFinal(this.cryptText));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();

    }

    @Override
    public String loadInBackground() {
        SystemClock.sleep(5000);

        return String.format("расшифрованное сообщение: %s",decryptMsg());
    }
}
