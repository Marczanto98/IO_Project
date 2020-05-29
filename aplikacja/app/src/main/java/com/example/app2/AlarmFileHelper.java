package com.example.app2;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AlarmFileHelper {
    public static final String FILENAME = "newalarminfo.dat";

    public static void writeData(List<NewAlarm> items, Context context)
    {
        try {
            if(items != null) {
                FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                ArrayList<String> itemsToString = null;
                for (int i = 0; i < items.size(); i++) {
                    NewAlarm item = items.get(i);
                    String result = item.getName() + ";" + item.getTime() + ";" + item.isSwitchOn();
                    itemsToString.add(result);
                }
                oos.writeObject(itemsToString);
                oos.close();
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<NewAlarm> readData(Context context)
    {
        ArrayList<String> itemsList = new ArrayList<String>();
        try {
            FileInputStream fis = context.openFileInput((FILENAME));
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemsList = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            itemsList = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<NewAlarm> listAsAlarms = new ArrayList<NewAlarm>();
        for(int i=0; i<itemsList.size(); i++){
            String[] item = itemsList.get(i).split(";");
            NewAlarm alarm = new NewAlarm(item[0], item[1], Boolean.parseBoolean(item[2]));
            listAsAlarms.add(alarm);
        }
        return listAsAlarms;
    }
}
