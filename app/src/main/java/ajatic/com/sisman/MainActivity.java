package ajatic.com.sisman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
                        Intent ir = new Intent(MainActivity.this, tickets.class);
                        ir.putExtra("fullName", fullName);
                        ir.putExtra("idProfile", idprofile);
                        ir.putExtra("idArea", idArea);
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
}
