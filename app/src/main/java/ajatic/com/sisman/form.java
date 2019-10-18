package ajatic.com.sisman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class form extends AppCompatActivity {
    int formId;
    LinearLayout llFrom;
    generator creator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        llFrom = findViewById(R.id.llForm);
        creator = new generator(this, llFrom);
        Bundle data = getIntent().getExtras();
        formId = data.getInt("formId");
        Log.w("formId", "" + formId);
        petitionForm();
    }

    private void petitionForm() {
        String url = getText(R.string.url) + "/form.php";
        JSONObject jsonObjectPetition = new JSONObject();
        JSONObject jsonObjectForm = new JSONObject();
        try {
            jsonObjectForm.put("id", formId);
            jsonObjectPetition.put("form", jsonObjectForm);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(form.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObjectPetition, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.w("response", response.toString());
                try {
                    JSONObject jsonObjectAnswer = response.getJSONObject("answer");
                    if (jsonObjectAnswer.getInt("code") == 200) {
                        JSONArray questions = response.getJSONArray("questions");
                        for (int i = 0; i < questions.length(); i++) {
                            JSONObject question = questions.getJSONObject(i);
                            int id = question.getInt("id");
                            int typeId = question.getInt("tipoId");
                            String description = question.getString("descripcion");
                            JSONArray options = question.getJSONArray("options");

                            switch (typeId) {
                                case 1:

                                    creator.createTitle(description);

                                    break;

                                case 2:

                                    creator.createText(description);

                                    break;

                                case 3:

                                    creator.createEditText(description, options);

                                    break;

                                case 4:
                                    creator.createRadioGroup(description, options);
                                    break;

                                case 5:
                                    creator.createCheckBox(description, options);

                                    break;
                            }
                        }

                    }
                    creator.createButtonSave();
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
