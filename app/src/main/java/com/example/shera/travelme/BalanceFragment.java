package com.example.shera.travelme;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BalanceFragment extends Fragment {

    public TextView accountName;
    public TextView accountBalance;
    public TextView lastRechargeDate;
    public String userName;


    public BalanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_balance, container, false);
        View viewDrawer = inflater.inflate(R.layout.activity_main, container, false);
        TextView userNameTextView = (TextView) viewDrawer.findViewById(R.id.userName);
        userName = userNameTextView.getText().toString();
        accountName = (TextView)view.findViewById(R.id.txtViewAccName);
        accountBalance = (TextView)view.findViewById(R.id.txtViewAccBal);
        lastRechargeDate = (TextView)view.findViewById(R.id.txtViewLastRecharge);

        Button button = (Button) view.findViewById(R.id.btnRefresh);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.prgBarRefreshStatus) ;
                progressBar.setVisibility(View.VISIBLE);
                GetBalance getBalance = new GetBalance();
                getBalance.execute("http://travelme.site88.net/getCreditBalance.php?userName="+userName+"");
                progressBar.setVisibility(View.GONE);
            }
        });
        ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.prgBarRefreshStatus) ;
        progressBar.setVisibility(View.VISIBLE);
        new GetBalance().execute("http://travelme.site88.net/getCreditBalance.php?userName="+userName+"");
        progressBar.setVisibility(View.GONE);
        return view;
    }

    class GetBalance extends AsyncTask<String,String, String > {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0].trim());
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                while((line  = reader.readLine()) != null){
                    stringBuffer.append(line);
                }
                return stringBuffer.toString();
            }catch (Exception e){
                Log.d("HTTP","ERROR : " + e.getMessage());
            }finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ioe) {
                    Log.d("IO", "Buffered Reader Error");
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObjWhole = new JSONObject (result);
                int status = jsonObjWhole.getInt("status");
                if(status == 200){
                    JSONObject jsonRepsonse = jsonObjWhole.getJSONObject("response");
                    Account account = new Account();
                    account.setId(jsonRepsonse.getString("id"));
                    account.setUserId(jsonRepsonse.getString("userId"));
                    account.setCreditBalance(Float.parseFloat(jsonRepsonse.get("creditBalance").toString()));

                    String dtStart = jsonRepsonse.get("createdDate").toString();
                    String dtEnd = jsonRepsonse.get("modifiedDate").toString();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date createdDate = format.parse(dtStart);
                    Date modifiedDate = format.parse(dtEnd);
                    account.setCreatedDate(createdDate);
                    account.setModifiedDate(modifiedDate);

                    accountName.setText(userName);
                    accountBalance.setText(account.getCreditBalance()+"");
                    lastRechargeDate.setText(account.getModifiedDateToString());

                } else {
                    Toast.makeText(getActivity(), "Please try again later !", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException jsne) {
                jsne.printStackTrace();
            } catch (ParseException ex){
                ex.printStackTrace();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }
}