package ajatic.com.sisman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

    String areaId;
    String profileId;
    String fullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        Bundle datos = getIntent().getExtras();
        fullName = datos.getString("fullName");
        profileId = datos.getString("idProfile");
        areaId = datos.getString("idArea");
    }

    private void petitionForms() {
        String url = getText(R.string.url) + "/forms.php";
        JSONObject jsonObjectPetition = new JSONObject();
        JSONObject jsonObjectArea = new JSONObject();
        try {
            jsonObjectArea.put("id", areaId);
            jsonObjectPetition.put("area", jsonObjectArea);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestQueue requestQueue = Volley.newRequestQueue(tickets.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.w("response", response.toString());
                JSONObject jsonObjectAnswer = null;
                try {
                    jsonObjectAnswer = response.getJSONObject("answer");
                    if (jsonObjectAnswer.getInt("code") == 200) {
                        JSONArray forms = response.getJSONArray("forms");
                        for (int i = 0; i < forms.length(); i++) {
                            JSONObject form = forms.getJSONObject(i);
                            int id = form.getInt("id");
                            String name = form.getString("nombre");
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
