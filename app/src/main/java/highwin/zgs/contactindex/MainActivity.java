package highwin.zgs.contactindex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import highwin.zgs.contactindex.adapter.RecyclerViewAdapter;
import highwin.zgs.contactindex.string.HaoHanNames;

public class MainActivity extends AppCompatActivity {

    private ConTactIndexView contactIndexView;
    private List<HaoHanBean> mHaoHans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactIndexView = ((ConTactIndexView) findViewById(R.id.ctiv));
        final RecyclerView recyclerView = (RecyclerView) findViewById(getResources().getIdentifier("rv", "id", getPackageName()));
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        initHaoHan();
        recyclerView.setLayoutManager(linearLayoutManager);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mHaoHans);
        recyclerView.setAdapter(adapter);
        contactIndexView.setOnLetterSelectListener(new ConTactIndexView.OnLetterSelectListener() {
            @Override
            public void onLetterChange(String lastLetter, String currentLetter) {
                if (TextUtils.equals(lastLetter, currentLetter)) {
                    recyclerView.scrollToPosition(0);
                } else {
                    for (int i = 0; i < mHaoHans.size(); i++) {
                        Log.d("MainActivity", "mHaoHans.get(i).getPinyin()\t" + mHaoHans.get(i).getPinyin() + "\tcurrentLetter\t" + currentLetter);
                        if (mHaoHans.get(i).getPinyin().equals(currentLetter)) {
                            recyclerView.scrollToPosition(i);
                            // TODO: 2016/8/24 Move View To Top
                            //        linearLayoutManager.scrollToPositionWithOffset(i, viewHolderForAdapterPosition.itemView.getTop());
                            break;
                        }
                    }
                }
                Log.d("MainActivity", "\tlastLetter\t" + lastLetter + "\tcurrentLetter\t" + currentLetter);
            }
        });
    }

    public void initHaoHan() {
        mHaoHans = new ArrayList<>();
        for (int i = 0; i < HaoHanNames.NAMES.length; i++) {
            mHaoHans.add(new HaoHanBean(HaoHanNames.NAMES[i]));
        }
        Collections.sort(mHaoHans);//排序
    }
}
