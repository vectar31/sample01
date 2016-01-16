package com.example.shobhit.login;
// some comment
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String REGISTER_URL = "http://agni.iitd.ernet.in/cop290/assign0/register/";

    public static final String KEY_TEAMNAME = "teamname";
    public static final String KEY_ENTRY1 = "entry1";
    public static final String KEY_NAME1 = "name1";
    public static final String KEY_ENTRY2 = "entry2";
    public static final String KEY_NAME2 = "name2";
    public static final String KEY_ENTRY3 = "entry3";
    public static final String KEY_NAME3 = "name3";


    private EditText teamname;
    private EditText member1;
    private EditText entry1;
    private EditText member2;
    private EditText entry2;
    private EditText member3;
    private EditText entry3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        teamname = (EditText) findViewById(R.id.teamname);
        member1 = (EditText) findViewById(R.id.member1);
        entry1= (EditText) findViewById(R.id.entry1);
        member2 = (EditText) findViewById(R.id.member2);
        entry2= (EditText) findViewById(R.id.entry2);
        member3 = (EditText) findViewById(R.id.member3);
        entry3= (EditText) findViewById(R.id.entry3);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void registerUser(){

        final String team = teamname.getText().toString().trim();
        final String name1 = member1.getText().toString().trim();
        final String entryno1 = entry1.getText().toString().trim();
        final String name2 = member2.getText().toString().trim();
        final String entryno2 = entry2.getText().toString().trim();
        final String name3 = member3.getText().toString().trim();
        final String entryno3 = entry3.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_TEAMNAME,team);
                params.put(KEY_ENTRY1,entryno1);
                params.put(KEY_NAME1, name1);
                params.put(KEY_ENTRY2,entryno2);
                params.put(KEY_NAME2, name2);
                params.put(KEY_ENTRY3,entryno3);
                params.put(KEY_NAME3, name3);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.refresh){
            clearfields();
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearfields(){
        teamname.setText("");
        member1.setText("");
        entry1.setText("");
        member2.setText("");
        entry2.setText("");
        member3.setText("");
        entry3.setText("");
    }
//  Validation code
    public boolean validatename(String name)
    {
        boolean result=true;
        for(int index=0;index<name.length();index++)
        {
            char ch=name.charAt(index);
            if(!((ch>=65&&ch<=90)||(ch>=97&&ch<=122)||ch==' '))
            {
                result=false;
                break;
            }
        }
        return result;
    }
    public boolean isCaharacter(char ch)
    {
        if((ch>=65&&ch<=90)||(ch>=97&&ch<=122))
            return true;
        return false;
    }
    public boolean validateEntryNmber(String entrynumber)
    {
        boolean result=true;
        if(entrynumber.length()<11)
            result=false;
        String temp=entrynumber.substring(0, 4);
        if(!(temp.equals("2014")||temp.equals("2013")||temp.equals("2012")||temp.equals("2011")||temp.equals("2010")||temp.equals("2009")||temp.equals("2008")))
        {
            result=false;
        }
        if(!(isCaharacter(entrynumber.charAt(4))&&isCaharacter(entrynumber.charAt(5))))
        {
            result=false;
        }
        for(int index=6;index<entrynumber.length();index++)
        {
            char ch=entrynumber.charAt(index);
            if (ch <= 48 && ch>= 57)
            {
                result=false;
                break;
            }
        }
        return result;
    }
    public boolean validatedata()
    {
        boolean result=true;
        String teamnamecheck=teamname.getText().toString();
        String member1check=member1.getText().toString();
        String member2check=member2.getText().toString();
        String member3check=member3.getText().toString();
        String entry1check=entry1.getText().toString();
        String entry2check=entry2.getText().toString();
        String entry3check=entry3.getText().toString();
        if(!(validatename(teamnamecheck)))
        {
            result=false;
            teamname.setError("Invalid. Find better ones");
        }
        else if(!(validatename(member1check)))
        {
            result=false;
            member1.setError("Invalid. Forgot your name?");
        }
        else if(!(validatename(member2check)))
        {
            result=false;
            member2.setError("Invalid. Forgot your name?");
        }
        else if(!(validatename(member3check)))
        {
            result=false;
            member3.setError("Invalid. Forgot your name?");
        }

        /*

        else if(!(validateEntryNmber(entry1check)))
        {
            result=false;
            entry1.setError("Invalid, never marked your attendance?");
        }
        else if(!(validateEntryNmber(entry2check)))
        {
            result=false;
            entry2.setError("Invalid, never marked your attendance?");
        }
        else if(!(validateEntryNmber(entry3check)))
        {
            result=false;
            entry3.setError("Invalid, never marked your attendance?");
        }

        */


        else
        {
            result=true;
        }
        return result;

    }
    public void submit(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        boolean checkdata=validatedata();
        clearfields();
        if(checkdata)
        {
            registerUser();
            startActivity(intent);
        }

    }
}
