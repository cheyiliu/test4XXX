package com.example.test4listview;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
/**
 * 
 * 搜索页面，启动方式：SearchActivity.startMeForResult(...)
 *
 * @author houshengyong
 * @since 2015-5-29
 */
public class SearchActivity extends Activity implements ISearchOpCallback {
    public static final String INPUT_CONFIG = "search_config";

    public static void startMeForResult(Activity activityCtx, SearchConfig config, int requestCode) {
        if (null != activityCtx) {
            Intent intent = new Intent(activityCtx, SearchActivity.class);
            intent.putExtra(INPUT_CONFIG, config);
            activityCtx.startActivityForResult(intent, requestCode);
        }
    }

    // 搜索输入区
    private View mBtnCitySelect;
    private View mEditViewKeywordInput;

    // 搜索结果区
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private ListView mResutListView;

    // 搜索配置
    private SearchConfig mSearchConfig;
    
    //
    private List<Address> mResultList = new ArrayList<Address>();
    SearchAdapter mSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_main);
        mSearchAdapter = new SearchAdapter(mResultList, this);
        addTestData();
        initView();
        parseIntent();
        
    }

    private void addTestData() {// TODO to remove
        for (int i = 0; i < 100; i++) {
            mResultList.add(new Address(new Locale("language1", "country1", "variant")));
            mResultList.add(new Address(new Locale("language2", "country2", "variant")));
            mResultList.add(new Address(new Locale("language3", "country3", "variant")));
            mResultList.add(new Address(new Locale("language4", "country4", "variant")));
            mResultList.add(new Address(new Locale("language5", "country5", "variant")));
            mResultList.add(new Address(new Locale("language6", "country6", "variant")));   
        }

    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (null != intent) {
            mSearchConfig = (SearchConfig) intent.getSerializableExtra(INPUT_CONFIG);
        }
    }

    private void initView() {
        mBtnCitySelect = findViewById(R.id.search_top_view_btn_city_select);
        mEditViewKeywordInput = findViewById(R.id.search_top_view_edit_place_input);

        mLoadingView = findViewById(R.id.search_loading_view);
        mEmptyView = findViewById(R.id.search_result_empty_view);
        mErrorView = findViewById(R.id.search_error_view);
        mResutListView = (ListView) findViewById(R.id.search_result_listview);
        mResutListView.setAdapter(mSearchAdapter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.test4listview.ISearchOpCallback#onSearchStart()
     */
    @Override
    public void onSearchStart() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.test4listview.ISearchOpCallback#onSearchFail()
     */
    @Override
    public void onSearchFail() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.test4listview.ISearchOpCallback#onSearchSucess(java.util.List)
     */
    @Override
    public void onSearchSucess(List<Address> addressList) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.test4listview.ISearchOpCallback#onSearchSucess()
     */
    @Override
    public void onSearchSucess() {
    }

}
