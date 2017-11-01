package com.example.navigationmenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EmailFragment extends Fragment {
    //Muutujate lisamine:
    EditText editEmail, editSubject, editMessage;
    Button btn_Send;

    public EmailFragment() {}

    //Override meetod:
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_email, container, false);

        editEmail = (EditText) view.findViewById(R.id.editEmail); //Leiab EditText muutuja nimega 'editEmail'
        editSubject = (EditText) view.findViewById(R.id.editSubject); //Leiab EditText muutuja nimega 'editSubject'
        editMessage = (EditText) view.findViewById(R.id.editMessage); //Leiab EditText mutuja nimega 'editMessage'
        btn_Send = (Button) view.findViewById(R.id.btn_send); //Leiab Button muutuja nimega 'btn_send'
        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = editEmail.getText().toString(); //Muudab 'editEmail' muutuja "String" tüübiks
                String subject = editSubject.getText().toString(); //Muudab 'editSubject' muutuja "String" tüübiks
                String message = editMessage.getText().toString(); //Muudab 'editMessage' muutuja "String" tüübiks

                //Loob abstrakti Intent mille abil saadakse email:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                intent.setType("message/rfc822"); //Seab Abstrakti intent tüübiks 'message/rfc822'

                startActivity(Intent.createChooser(intent, "Select Email app")); //Annab valiku, millise vahendi abil soovid emaili saata
            }
        });
        return view; //Tagastab view
    }
}
