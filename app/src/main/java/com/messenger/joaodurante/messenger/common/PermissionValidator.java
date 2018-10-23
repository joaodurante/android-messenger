package com.messenger.joaodurante.messenger.common;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionValidator {

    public PermissionValidator(Activity activity, int requestCode){
        validatePermission(activity, requestCode);
    }

    private static boolean validatePermission(Activity activity, int requestCode){
        if(Build.VERSION.SDK_INT > 23) {
            String[] permissions = {
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS
            };
            List<String> permissionList = new ArrayList<String>();

            for (String permission : permissions) {
                if (!(ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED))
                    permissionList.add(permission);
            }

            if (permissionList.isEmpty())
                return true;
            else {
                String[] newPermission = new String[permissionList.size()];
                permissionList.toArray(newPermission);
                ActivityCompat.requestPermissions(activity, newPermission, requestCode);
            }
        }
        return true;
    }

}
