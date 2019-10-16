package ajatic.com.sisman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class tickets extends AppCompatActivity {

    int userId;
    int areaId;
    int profileId;
    String fullName;
    TextView tvFullName;
    LinearLayout llForms;
    generator creator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        tvFullName = findViewById(R.id.tvFullName);
        llForms = findViewById(R.id.llForms);

        Bundle data = getIntent().getExtras();
        fullName = data.getString("fullName");
        profileId = data.getInt("idProfile");
        areaId = data.getInt("idArea");
        userId = data.getInt("idUser");

        creator = new generator(this, llForms);

        tvFullName.setText(fullName);
        petitionForms();
    }

    private void petitionForms() {
        String url = getText(R.string.url) + "/forms.php";
        JSONObject jsonObjectPetition = new JSONObject();
        JSONObject jsonObjectArea = new JSONObject();
        try {
            jsonObjectArea.put("idArea", areaId);
            jsonObjectArea.put("idUsuario", userId);
            jsonObjectPetition.put("tickets", jsonObjectArea);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(tickets.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObjectPetition, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.w("response", response.toString());
                try {
                    JSONObject jsonObjectAnswer = response.getJSONObject("answer");
                    if (jsonObjectAnswer.getInt("code") == 200) {
                        JSONArray forms = response.getJSONArray("forms");
                        for (int i = 0; i < forms.length(); i++) {
                            JSONObject form = forms.getJSONObject(i);
                            int id = form.getInt("id");
                            String name = form.getString("nombre");

                            creator.createListForms(name, id);
                        }

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
