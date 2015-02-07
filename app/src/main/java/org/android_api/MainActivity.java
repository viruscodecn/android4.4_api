package org.android_api;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCreateImageButton();
        initLoginButton();
    }

    private void initCreateImageButton(){
        Button imageTestView=(Button)findViewById(R.id.button);
        imageTestView.setOnClickListener(imageButtonImageClick);
    }

    private Button.OnClickListener imageButtonImageClick =new Button.OnClickListener(){
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,ImageActivity.class);
            startActivity(intent);
        }
    };

    private void initLoginButton(){
        Button loginTestButton=(Button)findViewById(R.id.login_button);
        loginTestButton.setOnClickListener(loginButtonClick);
    }

    private Button.OnClickListener loginButtonClick =new Button.OnClickListener(){
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
