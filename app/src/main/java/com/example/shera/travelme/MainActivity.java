package com.example.shera.travelme;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity{

    private static String TAG = MainActivity.class.getSimpleName();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView txtViewUserName = (TextView)findViewById(R.id.userName);
        new GetUserImage().execute("http://travelme.site88.net/getUserImage.php?userName="+txtViewUserName.getText()+"");

        mNavItems.add(new NavItem("Journey", "Setup new Journey", R.drawable.ic_menu_home));
        mNavItems.add(new NavItem("History", "View Journey History Stats", R.drawable.history_journey));
        mNavItems.add(new NavItem("Balance", "View Balance", R.drawable.credit_check));
        mNavItems.add(new NavItem("Profile", "Customize User Profile", R.drawable.user_profile));
        mNavItems.add(new NavItem("Settings", "Settings", R.drawable.ic_action_settings));
        mNavItems.add(new NavItem("About", "Get to know about us", R.drawable.ic_action_about));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }


    private void selectItemFromDrawer(int position) {
        Fragment fragment;
        FragmentManager fragmentManager;

        switch (position){
            case 0: break;
            case 1: break;
            case 2:
                fragment = new BalanceFragment();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.mainContent, fragment)
                        .commit();

                mDrawerList.setItemChecked(position, true);
                setTitle(mNavItems.get(position).mTitle);

                // Close the drawer
                mDrawerLayout.closeDrawer(mDrawerPane);
                break;
            case 3:break;

            case 4:
                fragment = new PreferencesFragement();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.mainContent, fragment)
                        .commit();

                mDrawerList.setItemChecked(position, true);
                setTitle(mNavItems.get(position).mTitle);

                // Close the drawer
                mDrawerLayout.closeDrawer(mDrawerPane);
                break;
            case 5:
                fragment = new AboutFragment();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.mainContent, fragment)
                        .commit();

                mDrawerList.setItemChecked(position, true);
                setTitle(mNavItems.get(position).mTitle);

                // Close the drawer
                mDrawerLayout.closeDrawer(mDrawerPane);
                break;
            default: break;
        }
    }


    //    // Called when invalidateOptionsMenu() is invoked
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        //menu.findItem(R.id.action_search).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }
//
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    }

    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            } else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText(mNavItems.get(position).mTitle);
            subtitleView.setText(mNavItems.get(position).mSubtitle);
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }

    class GetUserImage extends AsyncTask<String,String, String > {

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
                while ((line = reader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                return stringBuffer.toString();
            } catch (Exception e) {
                Log.d("HTTP", "ERROR : " + e.getMessage());
            } finally {
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
            JSONObject jsonObjWhole = null;
            try {
                jsonObjWhole = new JSONObject(result);
                int status = jsonObjWhole.getInt("status");
                if(status == 200){
                    JSONObject jsonRepsonse = jsonObjWhole.getJSONObject("response");
                    User user = new User();
                    user.setId(jsonRepsonse.getString("id"));
                    user.setUserName(jsonRepsonse.getString("userName"));
                    user.setFirstName(jsonRepsonse.getString("firstName"));
                    user.setLastName(jsonRepsonse.getString("lastName"));

                    byte[] decodedString = Base64.decode(jsonRepsonse.getString("image"), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    user.setImage(decodedByte);

                    user.setEmail(jsonRepsonse.getString("email"));
                    user.setPassword(jsonRepsonse.getString("password"));
                    user.setType(jsonRepsonse.getString("type"));
                    user.setLastKnownLocation(null);

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date createdDate = format.parse(jsonRepsonse.getString("createdDate").toString());
                    Date modifiedDate = format.parse(jsonRepsonse.getString("modifiedDate").toString());
                    user.setCreatedDate(createdDate);
                    user.setModifiedDate(modifiedDate);

                    ImageView userImage = (ImageView) findViewById(R.id.avatar);
                    userImage.setImageBitmap(user.getImage());

                    TextView userFullName = (TextView) findViewById(R.id.desc);
                    userFullName.setText(user.getFirstName() + " " + user.getLastName());

                } else {
                Toast.makeText(getBaseContext(), "Please try again later !", Toast.LENGTH_SHORT).show();
            }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}