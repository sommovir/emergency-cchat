package cyber.polygon.ecchat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import cyber.polygon.ecchat.mqtt.MQTTManager;

public class Settings {
    private static Settings _instance = null;
    private static Context context = null;
    private static MQTTManager manager = null;

    public static Settings getInstance(Context ctx, MQTTManager mng){
        System.out.println("ISTANZONE");
        if(manager == null){
            manager = mng;
        }
        if(context == null){
            context = ctx;
        }
        if(_instance == null){
            _instance = new Settings();
        }

        return _instance;
    }

    private Settings(){
        loadSettings();
    }

    private boolean googleSpeechEnabled = false;
    private String username = "gino";

    private void loadSettings(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);


        if(sharedPref != null){

            googleSpeechEnabled = sharedPref.getBoolean("googleSTT", false);
            username = sharedPref.getString("signature", "gino");
            //googleSpeechEnabled = Boolean.parseBoolean(sharedPref.getString("googleSTT", "false"));
            System.out.println("PREFERENCE FOUND: google -> "+googleSpeechEnabled);
            if(!username.equals("gino")) {
                manager.changeName(username);
            }

            sharedPref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if(key.equals("googleSTT")){
                        googleSpeechEnabled = sharedPreferences.getBoolean("googleSTT", false);
                    }
                    if(key.equals("signature")){
                        username = sharedPreferences.getString("signature", "gino");
                        System.out.println("SIGNATUREEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                        manager.changeName(username);
                    }
                }
            });
        }else{
            System.err.println("NO PREFERENZO");
        }
    }

    public String getUsername(){return username;}
    public boolean isGoogleSpeechEnabled() {
        return googleSpeechEnabled;
    }
}
