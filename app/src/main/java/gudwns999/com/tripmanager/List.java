package gudwns999.com.tripmanager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 2016-05-09.
 */
public class List extends Activity {
    String name;//s
    //URL
    String local_name[]={"국민대","광화문","하늘공원","성수대교","창덕궁","석촌호수"};

    String local_img[]={"https://goo.gl/qu8PHp",
            "https://goo.gl/CGHgVy",
            "https://goo.gl/431nYn",
            "https://goo.gl/X5FkzX",
            "https://goo.gl/YQj8Da",
            "https://goo.gl/PhpTVP" };

    private ListView listView = null;
    private ListViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        listView = (ListView)findViewById(R.id.list_List);
        adapter = new ListViewAdapter(this);
        listView.setAdapter(adapter);
        //Main으로 부터 가져오기
        Intent getIntent = getIntent();
        final String user_ID = getIntent.getExtras().getString("USERID");
        final String local_Num = getIntent.getExtras().getString("LOCALNUM");
        if(local_Num.charAt(0) == '1'){   adapter.addItem("1", local_name[0]);}
        if(local_Num.charAt(1) == '1'){   adapter.addItem("2", local_name[1]);}
        if(local_Num.charAt(2) == '1'){   adapter.addItem("3", local_name[2]);}
        if(local_Num.charAt(3) == '1'){   adapter.addItem("4", local_name[3]);}
        if(local_Num.charAt(4) == '1'){   adapter.addItem("5", local_name[4]);}
        if(local_Num.charAt(5) == '1'){   adapter.addItem("6", local_name[5]);}

        //List내용이 클릭되면 이벤트 -> 그 정보 상세보기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ListData mData = adapter.mListData.get(position);
            }
        });
    }
    //생명주기
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(List.this,name+" 눌렀었음!", Toast.LENGTH_SHORT).show();

        // The activity is about to become visible.
    }
    private class ViewHolder {
        public TextView location_num;
        public TextView location_name;
    }
    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<ListData> mListData = new ArrayList<ListData>();

        public ListViewAdapter(Context mContext) {
            super();
            this.mContext = mContext;
        }
        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(String location_num, String location_name){
            ListData addInfo = null;
            addInfo = new ListData();
            addInfo.location_num = location_num;
             addInfo.location_name = location_name;
            mListData.add(addInfo);
        }

        public void remove(int position){
            mListData.remove(position);
            dataChange();
        }

        public void sort(){
            Collections.sort(mListData, ListData.ALPHA_COMPARATOR);
            dataChange();
        }

        public void dataChange(){
            this.notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_layout, null);

                holder.location_num = (TextView) convertView.findViewById(R.id.no_text);
                holder.location_name = (TextView) convertView.findViewById(R.id.location_text);

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            ListData mData = mListData.get(position);

            holder.location_num.setText(mData.location_num);
            holder.location_name.setText(mData.location_name);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String num = mListData.get(position).localNum();
                    name = mListData.get(position).localName();
                    Intent intent = new Intent(List.this, MoreInfo.class);
                    intent.putExtra("LOCAL_URL",num);
                   startActivity(intent);
                }
            });
            return convertView;

        }

    }
}