package com.android.listview;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.listview.adapter.ArrayAdapterClass;
import com.android.listview.volleyutils.VolleySingleton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Volley";

    ListView nameList;
    ArrayList<String> list;
    ArrayAdapterClass adapter;

    RequestQueue requestQueue;

    private ImageLoader imageLoader;
    private NetworkImageView imageView;
    private static final String IMAGE_URL = "https://inducesmile.com/wp-content/uploads/2015/03/mobile12.jpg";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameList = findViewById(R.id.namelist);
        requestQueue = Volley.newRequestQueue(this);

        imageLoader = VolleySingleton.getInstance(getApplicationContext()).getImageLoader();
        imageView = (NetworkImageView) findViewById(R.id.networkImageView);

        imageLoader.get(IMAGE_URL,ImageLoader.getImageListener(imageView,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher));
        imageView.setImageUrl(IMAGE_URL, imageLoader);


    }

    private void setList() {
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile","Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

         adapter = new ArrayAdapterClass(this,1,R.layout.listrow,list);
//        final ArrayAdapter adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, list);

        nameList.setAdapter(adapter);
//        setListAdapter(adapter);

        nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,final View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
                Snackbar.make(view, "Here's a Snackbar with name"+list.get(position), Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                final String item = (String) adapterView.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }
        });

    }

    public void click(View view){
        switch (view.getId()){
            case R.id.arraylist:{
                setList();
                break;
            }
            case R.id.imagelist:{
                setImageList();
                break;
            }
        }
    }

    private void setImageList() {
        /*Json Request*/
        String URL = "https://inducesmile.com/wp-content/uploads/2015/03/mobile.jpg";
        ImageRequest imageRequest = new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                // Assign the response to an ImageView
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(response);
            }
        }, 100, 100, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
//                Bitmap bmp = createBitmapFromResource();
                Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
                imageView.setImageDrawable(d);
//                Log.d(TAG," size:: "+ bmp.getByteCount());
//                imageView.setImageBitmap(bmp);
                Log.d(TAG,"onErrorResponse");
            }
        });
        //add request to queue
        requestQueue.add(imageRequest);
    }

    private Bitmap createBitmapFromResource() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher,options);
        options.inSampleSize = calculateInSampleSize(options, 100, 100);
        options.inJustDecodeBounds = false;
        return  BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher,options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
