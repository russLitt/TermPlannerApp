package com.example.termplannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termplannerapp.database.CourseEntity;
import com.example.termplannerapp.database.TermEntity;
import com.example.termplannerapp.ui.CoursesAdapter;
import com.example.termplannerapp.ui.TermsAdapter;
import com.example.termplannerapp.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private List<TermEntity> termsData = new ArrayList<>();
    private List<CourseEntity> coursesData = new ArrayList<>();

    private CoursesAdapter mCoursesAdapter;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {

        final Observer<List<TermEntity>> termsObserver = new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                termsData.clear();
                termsData.addAll(termEntities);

                if (mTermsAdapter == null) {
                    mTermsAdapter = new TermsAdapter(termsData,
                            MainActivity.this);
                    mTermRecyclerView.setAdapter(mTermsAdapter);
                } else {
                    mTermsAdapter.notifyDataSetChanged();
                }
            }
        };

        final Observer<List<CourseEntity>> coursesObserver = new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                coursesData.clear();
                coursesData.addAll(courseEntities);
            }
        };

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.mTerms.observe(this, termsObserver);
        mViewModel.mCourses.observe(this, coursesObserver);
    }

    private void initRecyclerView() {
        mTermRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mTermRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(mTermRecyclerView.getContext(),
                layoutManager.getOrientation());
        mTermRecyclerView.addItemDecoration(divider);
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
        if (id == R.id.action_add_sample_data) {
            addSampleData();
            return true;
        } else if (id == R.id.action_delete_all) {
            deleteAllTerms();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllTerms() {
        mViewModel.deleteAllTerms();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}
