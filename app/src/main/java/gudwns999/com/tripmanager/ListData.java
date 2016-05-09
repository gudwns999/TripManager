package gudwns999.com.tripmanager;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by user on 2016-05-09.
 */
public class ListData {
    /**
     * 리스트 정보를 담고 있을 객체 생성
     */
    //지역번호
    public String location_num;
    //지역이름
    public String location_name;
    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<ListData> ALPHA_COMPARATOR = new Comparator<ListData>() {
        private final Collator sCollator = Collator.getInstance();
       @Override
        public int compare(ListData mListDate_1, ListData mListDate_2) {
            return sCollator.compare(mListDate_1.location_num, mListDate_2.location_num);
        }
    };
    public String localNum(){
        return location_num;
    }
    public String localName(){
        return location_name;
    }
}


