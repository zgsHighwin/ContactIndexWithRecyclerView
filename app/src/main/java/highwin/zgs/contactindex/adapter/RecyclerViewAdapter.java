package highwin.zgs.contactindex.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import highwin.zgs.contactindex.HaoHanBean;
import highwin.zgs.contactindex.R;

/**
 * User: zgsHighwin
 * Email: 799174081@qq.com Or 799174081@gmail.com
 * Description:
 * Create-Time: 2016/8/24 11:15
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {


    private List<HaoHanBean> mHaoHanList;
    private LayoutInflater mInflater;
    //   private String mPrePinyin = "";

    public RecyclerViewAdapter(Context context, List<HaoHanBean> haoHanList) {
        mInflater = LayoutInflater.from(context);
        this.mHaoHanList = haoHanList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_recycler, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        HaoHanBean haoHanBean = mHaoHanList.get(position);
        String pinyin = haoHanBean.getPinyin();
        if (position == 0) {
            holder.tvType.setVisibility(View.VISIBLE);
        } else {
            boolean equals = TextUtils.equals(mHaoHanList.get(position - 1).getPinyin(), pinyin);
            holder.tvType.setVisibility(equals ? View.GONE : View.VISIBLE);
        }
        holder.tvType.setText(pinyin);
        holder.tvName.setText(haoHanBean.getName());
    }

    @Override
    public int getItemCount() {
        return mHaoHanList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_type)
        TextView tvType;
        @InjectView(R.id.tv_name)
        TextView tvName;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
