package ajatic.com.sisman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
        Log.w("userTicket", "" + userId);
        tvFullName.setText(fullName);
        petitionForms();
    }

    private void petitionForms() {
        String url = getText(R.string.url) + "/tickets.php";
        JSONObject jsonObjectPetition = new JSONObject();
        JSONObject jsonObjectArea = new JSONObject();
        try {
            jsonObjectArea.put("idArea", areaId);
            jsonObjectArea.put("idUsuario", userId);
            jsonObjectPetition.put("tickets", jsonObjectArea);
            Log.w("JSONPeticion", jsonObjectPetition.toString());
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
                        JSONArray tickets = response.getJSONArray("tickets");
                        for (int i = 0; i < tickets.length(); i++) {
                            JSONObject ticket = tickets.getJSONObject(i);
                            int id = ticket.getInt("id");
                            String descripcion = ticket.getString("descripcion");
                            String servicio = ticket.getString("servicio");
                            String cliente = ticket.getString("cliente");
                            String celular = ticket.getString("celular");
                            int formularioId = ticket.getInt("formularioId");
                            //creator.createListForms(name, id);
                            createListTickets(id, descripcion, servicio, cliente, celular, formularioId);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("response", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void createListTickets(final int id, final String descripcion, String servicio, String cliente, final String celular, final int formularioId) {
        LinearLayout llBody = new LinearLayout(tickets.this);
        LinearLayout.LayoutParams paramsLlBody = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsLlBody.setMargins(0, 10, 0, 10);
        llBody.setLayoutParams(paramsLlBody);
        llBody.setOrientation(LinearLayout.HORIZONTAL);
        llBody.setGravity(Gravity.CENTER);
        llForms.addView(llBody);

        LinearLayout llTicket = new LinearLayout(tickets.this);
        LinearLayout.LayoutParams paramsLlTicket = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 8);
        llTicket.setLayoutParams(paramsLlTicket);
        llTicket.setOrientation(LinearLayout.VERTICAL);
        llTicket.setBackgroundResource(R.drawable.ticketd);
        llTicket.setGravity(Gravity.CENTER);
        llBody.addView(llTicket);

        TextView tvTicket = new TextView(tickets.this);
        LinearLayout.LayoutParams paramsTvTicket = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvTicket.setLayoutParams(paramsTvTicket);
        tvTicket.setText("Ticket #");
        tvTicket.setTextSize(15);
        tvTicket.setTextColor(Color.parseColor("#01090B"));
        llTicket.addView(tvTicket);

        TextView tvNumber = new TextView(tickets.this);
        LinearLayout.LayoutParams paramsTvNumber = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvNumber.setLayoutParams(paramsTvNumber);
        tvNumber.setText("" + id);
        tvNumber.setTextSize(50);
        tvNumber.setTextColor(Color.parseColor("#01090B"));
        llTicket.addView(tvNumber);

        LinearLayout llDescription = new LinearLayout(tickets.this);
        LinearLayout.LayoutParams paramsLlDescription = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2);
        llDescription.setLayoutParams(paramsLlDescription);
        llDescription.setOrientation(LinearLayout.VERTICAL);
        llDescription.setBackgroundResource(R.drawable.ticketi);
        llDescription.setPadding(20, 20, 20, 20);
        llBody.addView(llDescription);

        TextView tvService = new TextView(tickets.this);
        LinearLayout.LayoutParams paramsTvService = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvService.setLayoutParams(paramsTvService);
        tvService.setText(servicio);
        tvService.setTextSize(13);
        tvService.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        tvService.setTextColor(Color.parseColor("#01090B"));
        llDescription.addView(tvService);

        TextView tvClient = new TextView(tickets.this);
        LinearLayout.LayoutParams paramsTvClient = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvClient.setLayoutParams(paramsTvClient);
        tvClient.setText(cliente);
        tvClient.setTextSize(13);
        tvClient.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        tvClient.setTextColor(Color.parseColor("#01090B"));
        llDescription.addView(tvClient);

        LinearLayout llOptions = new LinearLayout(tickets.this);
        LinearLayout.LayoutParams paramsLlOptions = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llOptions.setLayoutParams(paramsLlOptions);
        llOptions.setOrientation(LinearLayout.HORIZONTAL);
        llDescription.addView(llOptions);

        Button btnDetails = new Button(tickets.this);
        LinearLayout.LayoutParams paramsbtnDetails = new LinearLayout.LayoutParams(120, 120);
        btnDetails.setLayoutParams(paramsbtnDetails);
        btnDetails.setBackgroundResource(R.drawable.ic_assignment_black_24dp);
        llOptions.addView(btnDetails);

        Button btnCall = new Button(tickets.this);
        LinearLayout.LayoutParams paramsBtnCall = new LinearLayout.LayoutParams(120, 120);
        btnCall.setLayoutParams(paramsBtnCall);
        btnCall.setBackgroundResource(R.drawable.ic_call_black_24dp);
        llOptions.addView(btnCall);

        Button btnArrow = new Button(tickets.this);
        LinearLayout.LayoutParams paramsbtnArrow = new LinearLayout.LayoutParams(120, 120);
        btnArrow.setLayoutParams(paramsbtnArrow);
        btnArrow.setBackgroundResource(R.drawable.ic_arrow_forward_black_24dp);
        llOptions.addView(btnArrow);

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(tickets.this);

                LinearLayout llContainer = new LinearLayout(tickets.this);
                LinearLayout.LayoutParams paramsLlContainer = new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT);
                llContainer.setLayoutParams(paramsLlContainer);
                llContainer.setPadding(10, 10, 10, 10);
                llContainer.setOrientation(LinearLayout.VERTICAL);

                TextView tvTitle = new TextView(tickets.this);
                LinearLayout.LayoutParams paramsTvTitle = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvTitle.setLayoutParams(paramsTvTitle);
                tvTitle.setText("DETALLE");
                tvTitle.setTextSize(40);
                llContainer.addView(tvTitle);

                ScrollView svDetail = new ScrollView(tickets.this);
                LinearLayout.LayoutParams paramsSvDetail = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                svDetail.setLayoutParams(paramsSvDetail);
                llContainer.addView(svDetail);

                LinearLayout llDetail = new LinearLayout(tickets.this);
                LinearLayout.LayoutParams paramsLlDetail = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                llDetail.setLayoutParams(paramsLlDetail);
                llDetail.setOrientation(LinearLayout.VERTICAL);
                svDetail.addView(llDetail);

                TextView tvDetail = new TextView(tickets.this);
                LinearLayout.LayoutParams paramsTvDetail = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvDetail.setLayoutParams(paramsTvDetail);
                tvDetail.setText(descripcion);
                tvDetail.setTextSize(20);
                llDetail.addView(tvDetail);

                Button btnDialog = new Button(tickets.this);
                LinearLayout.LayoutParams paramsBtnDialog = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                btnDialog.setLayoutParams(paramsBtnDialog);
                btnDialog.setText("Aceptar");
                llContainer.addView(btnDialog);

                btnDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(llContainer);
                dialog.show();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dial = "tel:" + celular;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });

        btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ir = new Intent(tickets.this, form.class);
                ir.putExtra("formId", formularioId);
                ir.putExtra("ticketId", id);
                ir.putExtra("idUser", userId);
                ir.putExtra("fullName", fullName);
                ir.putExtra("idProfile", profileId);
                ir.putExtra("idArea", areaId);
                startActivity(ir);
                finish();
            }
        });

    }
}
