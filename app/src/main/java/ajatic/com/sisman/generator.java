package ajatic.com.sisman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.ArrayList;
import java.util.Iterator;

public class generator {

    private Context context;
    private LinearLayout llContainer;
    private ArrayList<EditText> editTexts = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();
    public generator (Context context, LinearLayout llContainer) {
        this.context = context;
        this.llContainer = llContainer;
    }

    public void createListTickets(final int id, final String descripcion, String servicio, String cliente, final String celular, final int formularioId, String fullName, final int userId) {
        LinearLayout llBody = new LinearLayout(context);
        LinearLayout.LayoutParams paramsLlBody = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsLlBody.setMargins(0, 10, 0, 10);
        llBody.setLayoutParams(paramsLlBody);
        llBody.setOrientation(LinearLayout.HORIZONTAL);
        llBody.setGravity(Gravity.CENTER);
        llContainer.addView(llBody);

        LinearLayout llTicket = new LinearLayout(context);
        LinearLayout.LayoutParams paramsLlTicket = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,8);
        llTicket.setLayoutParams(paramsLlTicket);
        llTicket.setOrientation(LinearLayout.VERTICAL);
        llTicket.setBackgroundResource(R.drawable.ticketd);
        llTicket.setGravity(Gravity.CENTER);
        llBody.addView(llTicket);

        TextView tvTicket = new TextView(context);
        LinearLayout.LayoutParams paramsTvTicket = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvTicket.setLayoutParams(paramsTvTicket);
        tvTicket.setText("Ticket #");
        tvTicket.setTextSize(15);
        tvTicket.setTextColor(Color.parseColor("#01090B"));
        llTicket.addView(tvTicket);

        TextView tvNumber = new TextView(context);
        LinearLayout.LayoutParams paramsTvNumber = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvNumber.setLayoutParams(paramsTvNumber);
        tvNumber.setText("" + id);
        tvNumber.setTextSize(50);
        tvNumber.setTextColor(Color.parseColor("#01090B"));
        llTicket.addView(tvNumber);

        LinearLayout llDescription = new LinearLayout(context);
        LinearLayout.LayoutParams paramsLlDescription = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,2);
        llDescription.setLayoutParams(paramsLlDescription);
        llDescription.setOrientation(LinearLayout.VERTICAL);
        llDescription.setBackgroundResource(R.drawable.ticketi);
        llDescription.setPadding(20, 20, 20, 20);
        llBody.addView(llDescription);

        TextView tvService = new TextView(context);
        LinearLayout.LayoutParams paramsTvService = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvService.setLayoutParams(paramsTvService);
        tvService.setText(servicio);
        tvService.setTextSize(13);
        tvService.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        tvService.setTextColor(Color.parseColor("#01090B"));
        llDescription.addView(tvService);

        TextView tvClient = new TextView(context);
        LinearLayout.LayoutParams paramsTvClient = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvClient.setLayoutParams(paramsTvClient);
        tvClient.setText(cliente);
        tvClient.setTextSize(13);
        tvClient.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        tvClient.setTextColor(Color.parseColor("#01090B"));
        llDescription.addView(tvClient);

        LinearLayout llOptions = new LinearLayout(context);
        LinearLayout.LayoutParams paramsLlOptions = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llOptions.setLayoutParams(paramsLlOptions);
        llOptions.setOrientation(LinearLayout.HORIZONTAL);
        llDescription.addView(llOptions);

        Button btnDetails = new Button(context);
        LinearLayout.LayoutParams paramsbtnDetails = new LinearLayout.LayoutParams(120, 120);
        btnDetails.setLayoutParams(paramsbtnDetails);
        btnDetails.setBackgroundResource(R.drawable.ic_assignment_black_24dp);
        llOptions.addView(btnDetails);

        Button btnCall = new Button(context);
        LinearLayout.LayoutParams paramsBtnCall = new LinearLayout.LayoutParams(120, 120);
        btnCall.setLayoutParams(paramsBtnCall);
        btnCall.setBackgroundResource(R.drawable.ic_call_black_24dp);
        llOptions.addView(btnCall);

        Button btnArrow = new Button(context);
        LinearLayout.LayoutParams paramsbtnArrow = new LinearLayout.LayoutParams(120, 120);
        btnArrow.setLayoutParams(paramsbtnArrow);
        btnArrow.setBackgroundResource(R.drawable.ic_arrow_forward_black_24dp);
        llOptions.addView(btnArrow);

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);

                LinearLayout llContainer = new LinearLayout(context);
                LinearLayout.LayoutParams paramsLlContainer = new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT);
                llContainer.setLayoutParams(paramsLlContainer);
                llContainer.setPadding(10, 10, 10, 10);
                llContainer.setOrientation(LinearLayout.VERTICAL);

                TextView tvTitle = new TextView(context);
                LinearLayout.LayoutParams paramsTvTitle = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvTitle.setLayoutParams(paramsTvTitle);
                tvTitle.setText("DETALLE");
                tvTitle.setTextSize(40);
                llContainer.addView(tvTitle);

                ScrollView svDetail = new ScrollView(context);
                LinearLayout.LayoutParams paramsSvDetail = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                svDetail.setLayoutParams(paramsSvDetail);
                llContainer.addView(svDetail);

                LinearLayout llDetail = new LinearLayout(context);
                LinearLayout.LayoutParams paramsLlDetail = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                llDetail.setLayoutParams(paramsLlDetail);
                llDetail.setOrientation(LinearLayout.VERTICAL);
                svDetail.addView(llDetail);

                TextView tvDetail = new TextView(context);
                LinearLayout.LayoutParams paramsTvDetail = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvDetail.setLayoutParams(paramsTvDetail);
                tvDetail.setText(descripcion);
                tvDetail.setTextSize(20);
                llDetail.addView(tvDetail);

                Button btnDialog = new Button(context);
                LinearLayout.LayoutParams paramsBtnDialog = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                btnDialog.setLayoutParams(paramsBtnDialog);
                btnDialog.setText("Aceptar");
                llContainer.addView(btnDialog);

                btnDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });

        btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ir = new Intent(context, form.class);
                ir.putExtra("formId", formularioId);
                ir.putExtra("ticketId", id);
                ir.putExtra("userId", userId);
                context.startActivity(ir);
            }
        });

    }

    public void createListForms(String name, final int formId) {
        LinearLayout llBody = new LinearLayout(context);
        LinearLayout.LayoutParams paramsLlBody = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llBody.setLayoutParams(paramsLlBody);
        llBody.setOrientation(LinearLayout.VERTICAL);
        llBody.setGravity(Gravity.CENTER);
        llContainer.addView(llBody);

        ImageView ivForm = new ImageView(context);
        LinearLayout.LayoutParams paramsIvForm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150, 8);
        ivForm.setLayoutParams(paramsIvForm);
        ivForm.setImageResource(R.drawable.ic_assignment_black_24dp);
        llBody.addView(ivForm);

        TextView tvForm = new TextView(context);
        LinearLayout.LayoutParams paramsTvForm =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2);
        paramsIvForm.setMargins(0, 10, 0, 10);
        tvForm.setLayoutParams(paramsTvForm);
        tvForm.setText(name);
        llBody.addView(tvForm);

        ivForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir = new Intent(context, form.class);
                ir.putExtra("formId", formId);
                context.startActivity(ir);
            }
        });
    }

    public void createTitle(String description) {
        TextView tvTitle = new TextView(context);
        LinearLayout.LayoutParams paramsTvTitle =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTvTitle.gravity = Gravity.CENTER;
        tvTitle.setLayoutParams(paramsTvTitle);
        tvTitle.setTextSize(20f);
        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        tvTitle.setText(description);
        llContainer.addView(tvTitle);
    }

    public void createText(String description) {
        TextView tvText = new TextView(context);
        LinearLayout.LayoutParams paramsTvText =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTvText.setMargins(0, 10, 0, 0);
        tvText.setLayoutParams(paramsTvText);
        tvText.setTextSize(15f);
        tvText.setTextColor(Color.BLACK);
        tvText.setText(description);
        llContainer.addView(tvText);
    }

    public void createEditText(String description, JSONArray options) throws JSONException {
        createText(description);
        for (int i = 0; i < options.length(); i++) {
            JSONObject option = options.getJSONObject(i);
            EditText editText = new EditText(context);
            LinearLayout.LayoutParams paramsEditText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            editText.setLayoutParams(paramsEditText);
            editText.setId(option.getInt("id"));
            editText.setHint(option.getString("descripcion"));
            llContainer.addView(editText);
            editTexts.add(editText);
        }
    }

    public void createRadioGroup(String description, JSONArray options) throws JSONException {
        createText(description);
        RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        llContainer.addView(radioGroup);
        for (int i = 0; i < options.length(); i++) {
            JSONObject option = options.getJSONObject(i);
            RadioButton radioButton = new RadioButton(context);
            radioButton.setId(option.getInt("id"));
            radioButton.setText(option.getString("descripcion"));
            radioGroup.addView(radioButton);
            radioButtons.add(radioButton);
        }
    }

    public void createCheckBox(String description, JSONArray options) throws JSONException {
        createText(description);
        for (int i = 0; i < options.length(); i++) {
            JSONObject option = options.getJSONObject(i);
            CheckBox checkBox = new CheckBox(context);
            checkBox.setId(option.getInt("id"));
            checkBox.setText(option.getString("descripcion"));
            llContainer.addView(checkBox);
            checkBoxes.add(checkBox);
        }
    }

    public void createButtonSave(final int ticketId, final int userId){
        Button btnSave = new Button(context);
        LinearLayout.LayoutParams paramsBtnSave = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnSave.setLayoutParams(paramsBtnSave);
        btnSave.setText("Guardar");
        btnSave.setTextColor(Color.parseColor("#FFFFFF"));
        btnSave.setBackgroundResource(R.drawable.custonbutton);
        llContainer.addView(btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObjectPetition = new JSONObject();
                JSONObject jsonObjectTicket = new JSONObject();
                JSONArray jsonArrayAnswers = new JSONArray();
                try {
                    jsonObjectTicket.put("userId", userId);
                    jsonObjectTicket.put("ticketId", ticketId);
                    jsonObjectPetition.put("data", jsonObjectTicket);
                    for (Iterator iterator = editTexts.iterator(); iterator
                            .hasNext();) {
                        EditText editText = (EditText) iterator.next();
                        int optionId = editText.getId();
                        String detail = editText.getText().toString().trim();
                        if (!detail.equals("")) {
                            JSONObject answer = new JSONObject();
                            answer.put("optionId", optionId);
                            answer.put("detail", detail);
                            jsonArrayAnswers.put(answer);
                        } else {
                            JSONObject answer = new JSONObject();
                            answer.put("optionId", optionId);
                            answer.put("detail", "false");
                            jsonArrayAnswers.put(answer);
                        }
                    }

                    for (Iterator iterator = radioButtons.iterator(); iterator
                            .hasNext();) {
                        RadioButton radioButton = (RadioButton) iterator.next();
                        int optionId = radioButton.getId();
                        Boolean detail = radioButton.isChecked();
                        if (detail == true) {
                            JSONObject answer = new JSONObject();
                            answer.put("optionId", optionId);
                            answer.put("detail", "true");
                            jsonArrayAnswers.put(answer);
                        } else {
                            JSONObject answer = new JSONObject();
                            answer.put("optionId", optionId);
                            answer.put("detail", "false");
                            jsonArrayAnswers.put(answer);
                        }
                    }

                    for (Iterator iterator = checkBoxes.iterator(); iterator
                            .hasNext();) {
                        CheckBox checkBox = (CheckBox) iterator.next();
                        int optionId = checkBox.getId();
                        Boolean detail = checkBox.isChecked();
                        if (detail == true) {
                            JSONObject answer = new JSONObject();
                            answer.put("optionId", optionId);
                            answer.put("detail", "true");
                            jsonArrayAnswers.put(answer);
                        } else {
                            JSONObject answer = new JSONObject();
                            answer.put("optionId", optionId);
                            answer.put("detail", "false");
                            jsonArrayAnswers.put(answer);
                        }
                    }

                    jsonObjectPetition.put("answers", jsonArrayAnswers);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.w("send", jsonObjectPetition.toString());
                petitionLogin(jsonObjectPetition);
            }
        });
    }

    private void petitionLogin(JSONObject jsonObjectPetition) {

        String url = " ";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObjectPetition, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
