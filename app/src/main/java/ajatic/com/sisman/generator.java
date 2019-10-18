package ajatic.com.sisman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class generator {

    private Context context;
    private LinearLayout llContainer;
    public generator (Context context, LinearLayout llContainer) {
        this.context = context;
        this.llContainer = llContainer;
    }

    public void createListTickets(final int id, String descripcion, String servicio, String cliente, String celular, final int formularioId, String fullName, final int userId) {
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
        paramsLlDescription.setMargins(10, 0, 0, 10);
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
        }
    }

    public void createButtonSave(){
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

            }
        });
    }
}
