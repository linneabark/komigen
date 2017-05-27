package edu.chl.leep.ctrl;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import edu.chl.leep.model.ActivityRow;
import edu.chl.leep.model.Leep;
import edu.chl.leep.model.TimeLogModel;
import edu.chl.leep.service.QuotesService;

import com.example.linneabark.test.Convert;
import com.example.linneabark.test.R;

import edu.chl.leep.service.SaveActivity;
import edu.chl.leep.service.FileService;
import edu.chl.leep.utils.SaveDate;
import com.example.linneabark.test.unused.CategoryHashMap;

import java.util.Calendar;
import java.util.List;

import edu.chl.leep.model.Time;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeLog extends Fragment {
    //TODO TimeLogCtrl

    private TextView quoteDisplay;
    private QuotesService quote = new QuotesService();
    private SaveDate saveDate = new SaveDate();

    private long stopActivity;
    private long startActivity;
    private TextView time_txt;
    private CategoryHashMap cHM = new CategoryHashMap();
    SaveActivity saveActivity = new SaveActivity();
    Convert convert = new Convert();
    TimeLogModel timeLogModel;
    private Time time;
    Context mContext;


    /** Timer variables */
    private ImageButton timerButton;
    private TextView txtTimer;
    public TextView txtCountDown;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    public TimeLog() {
        // Required empty public constructor
    }
    Spinner spinner;

    public int position;

    public void setPosition(int value){
        position = value;

        System.out.println(value);
    }

    public int getPosition(){
        return position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_time_log, container, false);
        mContext = getActivity();

        timeLogModel = new TimeLogModel();


        //check whether or not the categories has been initialized with a name yet, should be in a seperate method
       timeLogModel.checkCategoryStatus(mContext);

        timeLogModel.checkQuoteStatus(mContext);

        /**SPINNER **/


        spinner = (Spinner)rootView.findViewById(R.id.spinner);
        ArrayAdapter<String> array = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, Leep.getCategoryList(mContext));
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(array);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                //parent.getItemAtPosition(position);

                setPosition(position+1);
                Toast.makeText(mContext,parent.getItemAtPosition(position) + " selected.", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }

        );

        /**TIMER **/

        quoteDisplay = (TextView) rootView.findViewById(R.id.quoteDisplay);
        Button stopClock = (Button) rootView.findViewById(R.id.stopClock_btn);
        final Button startClock = (Button) rootView.findViewById(R.id.startClock_btn);
        time_txt = (TextView) rootView.findViewById(R.id.clock_txt);
        time = Time.getInstance(this);


        updateText(time.toString());

        startClock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                time.startTimer();

                startActivity = System.currentTimeMillis();

                Toast.makeText(mContext, "Activity started!", Toast.LENGTH_SHORT).show();

            }
        });

        stopClock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                time.stopTimer();

                stopActivity = System.currentTimeMillis();

                saveActivity.addActivity(new ActivityRow(
                        Leep.getUSER(),
                        saveDate.calculateYearToString(),
                        saveDate.calculateMonthToString(),
                        saveDate.calculateDayToString(),
                        convert.longToString(startActivity),
                        convert.longToString(stopActivity - startActivity),
                        Leep.getCategory(mContext, getPosition())));

                System.out.println("THIS CATEGORYY????:" + Leep.getCategory(mContext,getPosition()));


                FileService.saveActivityToTxt(Leep.getUsername(mContext), SaveActivity.activityRowList, mContext);

                System.out.println("Which filename: "+ Leep.getUsername(mContext));
                System.out.println("Which filename2: "+ Leep.getUSER());

                List<ActivityRow> list = FileService.getActivityFromTxt(Leep.getUsername(mContext), mContext);

                //System.out.println(list);


                Toast.makeText(mContext, "Activity saved. Duration: " + saveDate.calculateTimeToString(stopActivity - startActivity), Toast.LENGTH_SHORT).show();

            }
        });

        quoteDisplay.setText(quote.getQuote());

        /** Timer, count down */
        // txtTimer = (TextView) rootView.findViewById(R.id.txtTimer);
        txtCountDown = (TextView) rootView.findViewById(R.id.timerText);
        timerButton = (ImageButton) rootView.findViewById(R.id.timerButton);

        timerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener, timeLogModel.getHour(), timeLogModel.getMinute(), true);

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.setTitle("Set time to start count down.");
                timePickerDialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /*
                System.out.println("hourofday " + hourOfDay);
                System.out.println("minute " + minute);
                String text = "Hour " + hourOfDay + ", minute " + minute + ".";
                txtTimer.setText(text);
                */

                time.startCountDown(hourOfDay, minute);
            }
        };

        return rootView;
    }

    public void updateText(final String text) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                time_txt.setText(text);

            }
        });
    }
}