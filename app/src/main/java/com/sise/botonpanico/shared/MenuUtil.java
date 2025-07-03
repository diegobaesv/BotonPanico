package com.sise.botonpanico.shared;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.Toast;

import com.sise.botonpanico.MainActivity;
import com.sise.botonpanico.R;
import com.sise.botonpanico.activities.InicioActivity;

public class MenuUtil {

    public static boolean onClickMenuVecinoItem(Activity activity, MenuItem item) {
        int idItem = item.getItemId();
        if(idItem == R.id.menuvecino_inicio) {
            Toast.makeText(activity, "menu inicioooo!",Toast.LENGTH_SHORT).show();
        } else if (idItem == R.id.menuvecino_misincidencias) {
            Toast.makeText(activity, "mis incidencias!",Toast.LENGTH_SHORT).show();
        } else if (idItem == R.id.menuvecino_cerrarsesion) {
            SharedPreferencesUtil.eliminar(activity, Constants.SHARED_PREFERENCES_USUARIO_LOGUEADO);
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
        return true;
    }
}
