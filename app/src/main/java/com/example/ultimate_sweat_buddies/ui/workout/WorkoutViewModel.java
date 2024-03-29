package com.example.ultimate_sweat_buddies.ui.workout;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WorkoutViewModel extends ViewModel {

    private int exerciseBeingEditedId = -1;

    public int getExerciseBeingEditedId() {
        return exerciseBeingEditedId;
    }

    public void setExerciseBeingEditedId(int exerciseBeingEditedId) {
        this.exerciseBeingEditedId = exerciseBeingEditedId;
    }

    public String getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.SUNDAY: return "Su";
            case Calendar.MONDAY: return "M";
            case Calendar.TUESDAY: return "Tu";
            case Calendar.WEDNESDAY: return "W";
            case Calendar.THURSDAY: return "Th";
            case Calendar.FRIDAY: return "F";
            case Calendar.SATURDAY: return "Sa";
            default: return null;
        }
    }

    public String getFormattedDateString(Date date, String formatPattern) {
        // create a format object using SimpleDateFormat class
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);

        // convert date to string timestamp
        String dateString = dateFormat.format(date);

        return dateString;
    }

    public void logWorkoutToFile(File filesDir, Date workoutDate, String output) {
        String filename = getFormattedDateString(workoutDate, "yyyyMMddHHmmss") + ".txt";
        try {
            File directory = new File(filesDir, "workout_logs");
            if (!directory.exists()) {
                if (!directory.mkdir()) {
                    Log.d("check_file_io", "directory not created");
                }
            }
            File file = new File(directory, filename);
            FileWriter writer = new FileWriter(file);
            writer.write(output);
            writer.flush();
            writer.close();

            Log.d("check_file_io", "write finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}