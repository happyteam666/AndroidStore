package com.example.androidstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.androidstore.R;
import com.example.androidstore.bean.CartInfo;
import com.example.androidstore.bean.CartItem;
import com.example.androidstore.callback.OnClickAddCloseListenter;
import com.example.androidstore.callback.OnClickDeleteListenter;
import com.example.androidstore.callback.OnClickListenterModel;
import com.example.androidstore.callback.OnViewItemClickListener;
import com.example.androidstore.widget.FrontViewToMove;
import com.example.androidstore.widget.PxxRoundOvalImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author mascot
 */
public class CartExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ListView listView;
    private volatile List<CartInfo.DataBean> list = new ArrayList<>();

    public CartExpandAdapter(Context context, ListView listView) {
        super();
        this.context = context;
        this.listView = listView;
    }

    public List<CartInfo.DataBean> getList() {
        return list;
    }

    public void setList(List<CartInfo.DataBean> dataBeans) {
        list.addAll(dataBeans);
        notifyDataSetChanged();
    }

    public void setList(CartInfo.DataBean dataBean) {
        list.add(dataBean);
        notifyDataSetChanged();
    }

    @Override
    public Object getChild(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return list.get(arg0).getItems().get(arg1);
    }

    @Override
    public long getChildId(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(final int groupPosition, final int position,
                             boolean arg2, View convertView, ViewGroup parent) {
        final ViewHolder1 viewHolder1;
        convertView = LayoutInflater.from(context).inflate(R.layout.cart_list_child_item, null);
        viewHolder1 = new ViewHolder1(convertView, groupPosition, position);

        //关键语句，使用自己写的类来对frontView的ontouch事件复写，实现视图滑动效果
        new FrontViewToMove(viewHolder1.frontView, listView, 200);

        setPagedata(groupPosition, position, viewHolder1);

        viewHolder1.checkBox.setOnClickListener(v -> onClickListenterModel.onItemClick(viewHolder1.checkBox.isChecked(), v, groupPosition, position));
        // 为button绑定事件，可以用此按钮来实现删除事件

        viewHolder1.button.setOnClickListener(v -> {
            onClickDeleteListenter.onItemClick(v, groupPosition, position);
            new FrontViewToMove(viewHolder1.frontView, listView, 200).generateRevealAnimate(viewHolder1.frontView, 0);
        });

        return convertView;
    }

    @SuppressLint("SetTextI18n")
    private void setPagedata(int groupPosition, int position, ViewHolder1 viewHolder1) {

        if ( list != null && list.size() != 0) {
            CartItem cartItem = list.get(groupPosition).getItems().get(position);
            viewHolder1.textView.setText(cartItem.getName());
            viewHolder1.checkBox.setChecked(cartItem.ischeck());
            viewHolder1.tvMoney.setText("¥ " + cartItem.getSpecifications().getPrice() * cartItem.getQuantity());
            viewHolder1.btnNum.setText(cartItem.getQuantity() + "");
            viewHolder1.chlidContent.setText(cartItem.getSpecifications().getName());
            viewHolder1.pxxRoundOvalImageView.setType(PxxRoundOvalImageView.TYPE_ROUND);
            viewHolder1.pxxRoundOvalImageView.setRoundRadius(8);

            Glide.with(context).load(list.get(groupPosition)
                    .getItems().get(position).getImage())
                    .into(viewHolder1.pxxRoundOvalImageView);
        }
    }

    class ViewHolder1 implements View.OnClickListener {
        private int groupPosition;
        private int position;
        private TextView textView;
        private View frontView;
        private Button button;
        private CheckBox checkBox;
        private PxxRoundOvalImageView pxxRoundOvalImageView;
        private TextView tvMoney;
        private Button btnAdd;
        private Button btnNum;
        private Button btnClose;
        private TextView chlidContent;

        ViewHolder1(View view, int groupPosition, int position) {
            this.groupPosition = groupPosition;
            this.position = position;
            pxxRoundOvalImageView = view.findViewById(R.id.item_chlid_image);
            textView = view.findViewById(R.id.item_chlid_name);
            checkBox = view.findViewById(R.id.item_chlid_check);
            button = view.findViewById(R.id.btn_delete);
            frontView = view.findViewById(R.id.id_front);
            tvMoney = view.findViewById(R.id.item_chlid_money);
            btnAdd = view.findViewById(R.id.item_chlid_add);
            btnAdd.setOnClickListener(this);
            btnNum = view.findViewById(R.id.item_chlid_num);
            btnClose = view.findViewById(R.id.item_chlid_close);
            btnClose.setOnClickListener(this);
            chlidContent = view.findViewById(R.id.item_chlid_content);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_chlid_add:
                    onClickAddCloseListenter.onItemClick(v, 2, groupPosition, position, Integer.valueOf(btnNum.getText().toString()));
                    break;
                case R.id.item_chlid_close:
                    onClickAddCloseListenter.onItemClick(v, 1, groupPosition, position, Integer.valueOf(btnNum.getText().toString()));
                    break;
                default:
            }
        }
    }

    /**
     * CheckBox1接口的方法
     */
    private OnViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * CheckBox2接口的方法
     */
    private OnClickListenterModel onClickListenterModel = null;

    public void setOnClickListenterModel(OnClickListenterModel listener) {
        this.onClickListenterModel = listener;
    }

    /**
     * 删除接口的方法
     */
    private OnClickDeleteListenter onClickDeleteListenter = null;

    public void setOnClickDeleteListenter(OnClickDeleteListenter listener) {
        this.onClickDeleteListenter = listener;
    }


    private OnClickAddCloseListenter onClickAddCloseListenter = null;

    public void setOnClickAddCloseListenter(OnClickAddCloseListenter listener) {
        this.onClickAddCloseListenter = listener;
    }


    @Override
    public int getChildrenCount(int arg0) {
        // TODO Auto-generated method stub
        return (list != null && list.size() > 0) ? list.get(arg0).getItems().size() : 0;
    }

    @Override
    public Object getGroup(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return (list != null && list.size() > 0) ? list.size() : 0;
    }

    @Override
    public long getGroupId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_list_group_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (groupPosition == 0) {
            viewHolder.textTopBar.setVisibility(View.GONE);
        }
        CartInfo.DataBean dataBean = (CartInfo.DataBean) getGroup(groupPosition);
        viewHolder.textView.setText(dataBean.getShopName());
        viewHolder.checkBox.setChecked(dataBean.ischeck());
        viewHolder.checkBox.setOnClickListener(v -> mOnItemClickListener.onItemClick(viewHolder.checkBox.isChecked(), v, groupPosition));
        viewHolder.textView.setOnClickListener(v -> Toast.makeText(context, "点击标题", Toast.LENGTH_LONG).show());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    class ViewHolder {
        CheckBox checkBox;
        TextView textView;
        TextView textTopBar;

        ViewHolder(View view) {
            textView = view.findViewById(R.id.shopName);
            checkBox = view.findViewById(R.id.check_box);
            textTopBar = view.findViewById(R.id.item_group_topbar);
        }
    }

}
