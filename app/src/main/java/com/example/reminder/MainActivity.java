package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    public static final String WEBSITE_TOEVOEGEN = "Website toevoegen";
    public static final String WEBSITE_OPENEN = "Website openen";
    public static final int REQUESTCODE = 1234;

    //instance variables
    private List<Website> mWebsites;
    private WebsiteAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private GestureDetector mGestureDetector;
    private int mModifyPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize the instance variables
        mRecyclerView = findViewById(R.id.recyclerView);
        mWebsites = new ArrayList<>();

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, REQUESTCODE);

            }
        });

        /*

Add a touch helper to the RecyclerView to recognize when a user swipes to delete a list entry.
An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
and uses callbacks to signal when a user is performing these actions.

*/

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =

                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override

                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                        return false;

                    }


                    //Called when a user swipes left or right on a ViewHolder

                    @Override

                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        mWebsites.remove(position);
                        mAdapter.notifyItemRemoved(position);
                    }

                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addOnItemTouchListener(this);
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new WebsiteAdapter(mWebsites);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


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

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        int mAdapterPosition = recyclerView.getChildAdapterPosition(child);
        if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
//            Toast.makeText(this, mWebsites.get(mAdapterPosition).getmWebsiteName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            mModifyPosition = mAdapterPosition;
            intent.putExtra(WEBSITE_OPENEN, mWebsites.get(mAdapterPosition));
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
                Website nieuweWebsite = data.getParcelableExtra(MainActivity.WEBSITE_TOEVOEGEN);
                // New timestamp: timestamp of update
                mWebsites.add(mModifyPosition, nieuweWebsite);
                updateUI();
            }
        }
    }


}
