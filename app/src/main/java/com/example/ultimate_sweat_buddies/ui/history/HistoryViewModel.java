package com.example.ultimate_sweat_buddies.ui.history;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
            if (files == null) {
                return fileContents;
            }

            // Sort by most recent to oldest
            Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));

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

    public void deleteAllWorkoutLogs(File filesDir) {
        File workoutLogsDir = new File(filesDir, "workout_logs");
        if (workoutLogsDir.isDirectory()) {
            String[] children = workoutLogsDir.list();
            for (String child : children) {
                if (!(new File(workoutLogsDir, child).delete())) {
                    Log.d("delete_workout_logs", "Failed to delete workout logs");
                }
            }
        }
    }
}