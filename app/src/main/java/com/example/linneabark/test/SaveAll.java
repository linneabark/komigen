package com.example.linneabark.test;

import android.content.Context;
import android.content.ContextWrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eli on 2017-05-15.
 */

public class SaveAll implements Serializable{

    String SAVED_INFO = "savedInfo.txt";

    private static String activityFolder = "Activities";





    public static void saveActivityToTxt(String filename, List<ActivityRow> list, Context mContext) { //filename aka username

        try {

            ContextWrapper cw = new ContextWrapper(mContext);
            File directory = cw.getDir(activityFolder, Context.MODE_PRIVATE);
            File myPath =new File(directory,filename + ".txt");

            FileOutputStream fos = new FileOutputStream(myPath);
            // Use the compress method on the BitMap object to write image to the OutputStream

            //list.toArray().toString();
            //FileOutputStream fos = mContext.openFileOutput((filename +".txt"), Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(list);
    }

    public static List getActivityFromTxt(String filename, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("users", Context.MODE_PRIVATE);
        // Create imageDir
        File myPath=new File(directory,filename + ".txt");


        List<ActivityRow> list = null;

        try {
            FileInputStream fis = new FileInputStream(myPath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            list = ((List <ActivityRow> )ois.readObject());
            ois.close();
            fis.close();
        }

            catch(ClassNotFoundException | IOException e ){
                e.printStackTrace();
            }

            System.out.println("THE RETRIEVED LIST: " + list);
            return list;
        }
    }

    /*spara user info som en lista i en textfil
    användarnamn
    lösenord
    email

    openSavedActivities() kommer skicka in användarnamnet som filnamn,
    och därefter skapa en fil med aktiviteterna till det användarnamnet
     */



