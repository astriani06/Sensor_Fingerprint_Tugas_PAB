package com.example.sensor_fingerprint_tugas_pab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    ConstraintLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainLayout=findViewById(R.id.main_layout);


        BiometricManager biometricManager=BiometricManager.from(this);
        switch (biometricManager.canAuthenticate())
        {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getApplicationContext(),"Perangkat Tidak memiliki sidik jari",Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(getApplicationContext(),"Not Working",Toast.LENGTH_SHORT).show();

                case  BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Toast.makeText(getApplicationContext(),"Tidak Ada Sidik Jari yang Ditugaskan",Toast.LENGTH_SHORT).show();

        }

        Executor executor= ContextCompat.getMainExecutor(this);

        biometricPrompt=new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                mMainLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo=new BiometricPrompt.PromptInfo.Builder().setTitle("Sensor FingerPrint")
                .setDescription("Gunakan Sidik Jari Untuk Masuk").setDeviceCredentialAllowed(true).build();

        biometricPrompt.authenticate(promptInfo);





    }
}