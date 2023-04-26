package ru.mirea.sidorov_m.data_thread;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ru.mirea.sidorov_m.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.textView1.setText("runn1");
            }
        };

        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.textView1.setText("runn2");
            }
        };

        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.textView1.setText("runn3");
            }
        };

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);

                    TimeUnit.SECONDS.sleep(1);
                    binding.textView1.postDelayed(runn3, 2000);

                    binding.textView1.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}