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

    public generator(Context context, LinearLayout llContainer) {
        this.context = context;
        this.llContainer = llContainer;
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
        LinearLayout.LayoutParams paramsTvForm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2);
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
        LinearLayout.LayoutParams paramsTvTitle = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
        LinearLayout.LayoutParams paramsTvText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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

    public JSONObject dataForm(final int ticketId, final int userId) {

        JSONObject jsonObjectPetition = new JSONObject();
        JSONObject jsonObjectTicket = new JSONObject();
        JSONArray jsonArrayAnswers = new JSONArray();
        try {
            jsonObjectTicket.put("userId", userId);
            jsonObjectTicket.put("ticketId", ticketId);
            jsonObjectPetition.put("data", jsonObjectTicket);
            for (Iterator iterator = editTexts.iterator(); iterator
                    .hasNext(); ) {
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
                    .hasNext(); ) {
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
                    .hasNext(); ) {
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

        return jsonObjectPetition;
    }
}
