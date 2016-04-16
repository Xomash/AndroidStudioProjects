package xoma.khomandiaklab07;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;
import org.json.JSONObject;

//Реалізувати аутентифікацію користувача через vk. На екран виводити Ім'я, прізвище, сімейний стан та стать. Реалізувати можливість logout
public class MainActivity extends AppCompatActivity {

    private String[] scope = new String[]{VKScope.MESSAGES, VKScope.FRIENDS, VKScope.WALL};
    private ListView listView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VKSdk.login(this, scope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
// Пользователь успешно авторизовался
                //listView = (ListView) findViewById(R.id.listView);
                textView1 = (TextView) findViewById(R.id.textView);
                textView2 = (TextView) findViewById(R.id.textView2);
                textView3 = (TextView) findViewById(R.id.textView3);
                textView4 = (TextView) findViewById(R.id.textView4);
                VKRequest vkRequest = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name, sex, relation"));
                VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "first_name", "last_name"));
                vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        VKApiUserFull user = ((VKList<VKApiUserFull>) response.parsedModel).get(0);
                        textView1.setText(user.first_name);
                        textView2.setText(user.last_name);
                        if (user.sex == VKApiUserFull.Sex.FEMALE)
                            textView3.setText("Жінка");
                        if (user.sex == VKApiUserFull.Sex.MALE)
                            textView3.setText("Чоловік");
                        switch (user.relation){
                            case 1:
                                textView4.setText("не женат/не замужем");
                            case 2:
                                textView4.setText("есть друг/есть подруга");
                            case 3:
                                textView4.setText("помолвлен/помолвлена");
                            case 4:
                                textView4.setText("женат/замужем");
                            case 5:
                                textView4.setText("всё сложно");
                            case 6:
                                textView4.setText(" в активном поиске");
                            case 7:
                                textView4.setText("влюблён/влюблена");
                            default:
                                textView4.setText("не указано");
                        }


                        /*
                        VKList list = (VKList) response.parsedModel;
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_expandable_list_item_1, list);
                        listView.setAdapter(arrayAdapter);
                        */

                    }
                });
                Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
