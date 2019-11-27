package com.example.myapplication.Utils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.WiFiBean;

import java.util.ArrayList;
import java.util.List;

public class testAdapter extends RecyclerView.Adapter<testAdapter.VH> {
    private List<WiFiBean> mDatas;

    public testAdapter(List<WiFiBean> mDatas) {
      this.mDatas = mDatas;
    }

    class VH extends RecyclerView.ViewHolder {
        TextView textView, qingdu, lock;
        Button button_position, button_qq;

        VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text2);
            qingdu = itemView.findViewById(R.id.qianddu);
            lock = itemView.findViewById(R.id.lock);
            button_position = itemView.findViewById(R.id.lll);

        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {

        holder.textView.setText(mDatas.get(position).getname());
        holder.qingdu.setText(mDatas.get(position).getRiss()+"");
        holder.lock.setText(mDatas.get(position).getLockkk());

        holder.button_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListerer.onItemClick(v, position);
                // Toast.makeText(mContext,"点击了"+position,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    /**
     * **************接口与回调
     ******************/


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    //点击事件的回调
    public testAdapter.OnItemClickListener mOnItemClickListerer;
    public testAdapter.OnItemClickListener msetQQClickListerer;

    public void setmOnItemClickListerer(testAdapter.OnItemClickListener listerer) {
        this.mOnItemClickListerer = listerer;
    }

    public void setmQQClickListerer(testAdapter.OnItemClickListener listerer) {
        this.msetQQClickListerer = listerer;
    }
}
