package edu.chl.leep.ctrl;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.linneabark.test.ExpandableListAdapter;
import com.example.linneabark.test.R;
import edu.chl.leep.model.Leep;
import edu.chl.leep.model.SettingsModel;
/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsController extends Fragment{

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private EditText categoryEdit;
    private EditText quotesEdit;
    private String buttonTag;
    private Button exitButtonHelp;
    private Button exitButtonCategory;
    private Button exitButtonQuotes;
    private Button saveButtonCategory;
    private Button saveButtonQuotes;
    private ImageButton popUpButton;
    private SettingsModel settingsModel;

    public SettingsController() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        settingsModel = new SettingsModel(getContext());
        settingsModel.initData();
        listView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(getActivity(), settingsModel.getListDataHeader(), settingsModel.getListHash(), listView);
        listView.setAdapter(listAdapter);
        System.out.println("Listview, sett: " + listView);

       return rootView;
    }

    public void choosePopUp(View v){
        popUpButton = (ImageButton) v.findViewById(R.id.list_item_button);
        buttonTag = popUpButton.getTag().toString();

        if (getExpanded() == 1){
            if(Integer.valueOf(buttonTag) == 0) {
                showCategoryPopUpOne();
            }else if (Integer.valueOf(buttonTag) == 1){
                showCategoryPopUpTwo();
            }else {
                showCategoryPopUpThree();
            }
        }else if(getExpanded() == 2){
            if(Integer.valueOf(buttonTag) == 0) {
                showQuotesPopUpOne();
            }else if(Integer.valueOf(buttonTag) == 1){
                showQuotesPopUpTwo();
            }else{
                showQuotesPopUpThree();
            }
        }else{
            showHelpPopUp();
        }
    }

    private void showCategoryPopUpOne(){

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View categoryLayout = inflater.inflate(R.layout.pop_up_category, null);
        helpBuilder.setView(categoryLayout);

        final AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

        categoryEdit = (EditText) categoryLayout.findViewById(R.id.edit_text_category);
        categoryEdit.setText(Leep.getCategory1(getContext()), TextView.BufferType.EDITABLE);

        saveButtonCategory = (Button) categoryLayout.findViewById(R.id.save_button_category);
        saveButtonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leep.setCategory1(getContext(), categoryEdit.getText().toString());
                helpDialog.dismiss();
            }
        });

        exitButtonCategory = (Button) categoryLayout.findViewById(R.id.close_button_category);
        exitButtonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpDialog.dismiss();
            }
        });
    }

    private void showCategoryPopUpTwo(){

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View categoryLayout = inflater.inflate(R.layout.pop_up_category, null);
        helpBuilder.setView(categoryLayout);

        final AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

        categoryEdit = (EditText) categoryLayout.findViewById(R.id.edit_text_category);
        categoryEdit.setText(Leep.getCategory2(getContext()), TextView.BufferType.EDITABLE);

        saveButtonCategory = (Button) categoryLayout.findViewById(R.id.save_button_category);
        saveButtonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leep.setCategory2(getContext(), categoryEdit.getText().toString());
                helpDialog.dismiss();
            }
        });

        exitButtonCategory = (Button) categoryLayout.findViewById(R.id.close_button_category);
        exitButtonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpDialog.dismiss();
            }
        });
    }

    private void showCategoryPopUpThree(){

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View categoryLayout = inflater.inflate(R.layout.pop_up_category, null);
        helpBuilder.setView(categoryLayout);

        final AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

        categoryEdit = (EditText) categoryLayout.findViewById(R.id.edit_text_category);
        categoryEdit.setText(Leep.getCategory3(getContext()), TextView.BufferType.EDITABLE);

        saveButtonCategory = (Button) categoryLayout.findViewById(R.id.save_button_category);
        saveButtonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leep.setCategory3(getContext(), categoryEdit.getText().toString());
                helpDialog.dismiss();
            }
        });

        exitButtonCategory = (Button) categoryLayout.findViewById(R.id.close_button_category);
        exitButtonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpDialog.dismiss();
            }
        });
    }

    private void showQuotesPopUpOne() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View quotesLayout = inflater.inflate(R.layout.pop_up_quotes, null);
        helpBuilder.setView(quotesLayout);

        final AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

        quotesEdit = (EditText) quotesLayout.findViewById(R.id.edit_text_quotes);
        quotesEdit.setText(Leep.getQuote1(getContext()), TextView.BufferType.EDITABLE);

        saveButtonQuotes = (Button) quotesLayout.findViewById(R.id.save_button_quotes);
        saveButtonQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leep.setQuote1(getContext(), quotesEdit.getText().toString());
                helpDialog.dismiss();
            }
        });

        exitButtonQuotes = (Button) quotesLayout.findViewById(R.id.close_button_quotes);
        exitButtonQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpDialog.dismiss();
            }
        });
    }

    private void showQuotesPopUpTwo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View quotesLayout = inflater.inflate(R.layout.pop_up_quotes, null);
        helpBuilder.setView(quotesLayout);

        final AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

        quotesEdit = (EditText) quotesLayout.findViewById(R.id.edit_text_quotes);
        quotesEdit.setText(Leep.getQuote2(getContext()), TextView.BufferType.EDITABLE);

        saveButtonQuotes = (Button) quotesLayout.findViewById(R.id.save_button_quotes);
        saveButtonQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leep.setQuote2(getContext(), quotesEdit.getText().toString());
                helpDialog.dismiss();
            }
        });

        exitButtonQuotes = (Button) quotesLayout.findViewById(R.id.close_button_quotes);
        exitButtonQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpDialog.dismiss();
            }
        });
    }

    private void showQuotesPopUpThree() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View quotesLayout = inflater.inflate(R.layout.pop_up_quotes, null);
        helpBuilder.setView(quotesLayout);

        final AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

        quotesEdit = (EditText) quotesLayout.findViewById(R.id.edit_text_quotes);
        quotesEdit.setText(Leep.getQuote3(getContext()), TextView.BufferType.EDITABLE);

        saveButtonQuotes = (Button) quotesLayout.findViewById(R.id.save_button_quotes);
        saveButtonQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leep.setQuote3(getContext(), quotesEdit.getText().toString());
                helpDialog.dismiss();
            }
        });

        exitButtonQuotes = (Button) quotesLayout.findViewById(R.id.close_button_quotes);
        exitButtonQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpDialog.dismiss();
            }
        });
    }

    private void showHelpPopUp() {

        final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View helpLayout = inflater.inflate(R.layout.pop_up_help, null);
        helpBuilder.setView(helpLayout);

        final AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
        exitButtonHelp = (Button) helpLayout.findViewById(R.id.done_button_help);
        exitButtonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpDialog.dismiss();
            }
        });
    }

    public int getExpanded(){

        if (listView.isGroupExpanded(0)){
            return 1;
        }else if (listView.isGroupExpanded(1)){
            return 2;
        }else if(listView.isGroupExpanded(2)){
            return 3;
        }
        return 0;
}}