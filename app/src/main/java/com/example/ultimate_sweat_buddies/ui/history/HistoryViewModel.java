package com.example.ultimate_sweat_buddies.ui.history;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel {

    public List<String> readLoggedWorkouts(File filesDir) {
        List<String> fileContents = new ArrayList<>();
        try {
            File directory = new File(filesDir, "workout_logs");
            if (!directory.exists()) {
                return fileContents;    // Empty ArrayList
            }
            File[] files = directory.listFiles();
            for (File file : files) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                br.close();
                fileContents.add(sb.toString());

                Log.d("check_file_io", "1 read finished");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }
}