package ajatic.com.sisman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnStart = findViewById(R.id.btnStart);
        validarPermisos();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().trim().equals("") || etPassword.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, getText(R.string.loginMessageNegative), Toast.LENGTH_LONG).show();
                } else {
                    petitionLogin();
                }
            }
        });
    }

    private void petitionLogin() {
        String url = getText(R.string.url) + "/login.php";
        JSONObject jsonObjectPetition = new JSONObject();
        JSONObject jsonObjectAuthentication = new JSONObject();
        try {
            jsonObjectAuthentication.put("email", etEmail.getText().toString().trim());
            jsonObjectAuthentication.put("password", etPassword.getText().toString().trim());
            jsonObjectPetition.put("authentication", jsonObjectAuthentication);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObjectPetition, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.w("response", response.toString());
                JSONObject jsonObjectAnswer = null;
                try {
                    jsonObjectAnswer = response.getJSONObject("answer");
                    if (jsonObjectAnswer.getInt("code") == 200) {
                        JSONObject jsonObjectUser = response.getJSONObject("user");
                        String fullName = jsonObjectUser.getString("fullName");
                        int idprofile = jsonObjectUser.getInt("idPerfil");
                        int idArea = jsonObjectUser.getInt("idArea");
                        int idUser = jsonObjectUser.getInt("idUser");
                        Intent ir = new Intent(MainActivity.this, tickets.class);
                        ir.putExtra("fullName", fullName);
                        ir.putExtra("idProfile", idprofile);
                        ir.putExtra("idArea", idArea);
                        ir.putExtra("idUser", idUser);
                        startActivity(ir);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /******************/
    private boolean validarPermisos(){

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            return true;

        }
        if((checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED)){

            return true;

        }

        if ((shouldShowRequestPermissionRationale(CALL_PHONE))) {

            cargardialogo();

        }
        else {

            requestPermissions(new String[]{CALL_PHONE}, 100);

        }

        return false;

    }

    private void cargardialogo(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Permisos Desactivados");
        builder.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{CALL_PHONE}, 100);
                }

            }
        });
        builder.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100) {

            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {



            } else {

                cargardialogo2();

            }

        }

    }

    private void cargardialogo2(){

        final CharSequence[] op = {"si", "no"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Desea configurar los permisos manualmente?");
        builder.setItems(op, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(op[which].equals("si")){

                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent .setData(uri);
                    startActivity(intent);

                }
                else {

                    Toast.makeText(MainActivity.this, "los permisos no fueron aceptados", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }

            }
        });
        builder.show();

    }
}
