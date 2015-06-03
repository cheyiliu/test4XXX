import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-3
 */

public class Main {
    public static void main(String[] args) {
        {// the output order is same as the insert order
            LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
            initMap(map);
            Set<Entry<Integer, String>> entrySet = map.entrySet();
            Iterator<Entry<Integer, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<Integer, String> entry = iterator.next();
                System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
            }
        }
        System.out.println("-----------------------");
        {// the output order is NOT same as the insert order
            HashMap<Integer, String> map = new HashMap<>();
            initMap(map);
            Set<Entry<Integer, String>> entrySet = map.entrySet();
            Iterator<Entry<Integer, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<Integer, String> entry = iterator.next();
                System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
            }
        }
        
        System.out.println("-----------------------");
        {// the output order is NOT same as the insert order, but sorted by keys
            Map<Integer, String> map = new TreeMap<Integer, String>();
            initMap(map);
            Set<Entry<Integer, String>> entrySet = map.entrySet();
            Iterator<Entry<Integer, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<Integer, String> entry = iterator.next();
                System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
            }
        }
    }

    private static void initMap(Map<Integer, String> map) {
        map.put(11111111, "11111");
        map.put(21222, "22111");
        map.put(333, "33111");
        map.put(111111, "11111");
        map.put(211511, "22111");
        map.put(316111, "33111");
        map.put(118111, "11111");
        map.put(211511, "22111");
        map.put(311191, "33111");
    }
}
