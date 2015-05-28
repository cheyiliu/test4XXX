import java.lang.ref.SoftReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class test4HashMap {

    /**
     * @param args
     */
    public static void main(String[] args) {
        MyClass myClass = new MyClass("123");
        HashMap<Integer, SoftReference<MyClass>> map = new HashMap<>();
        map.put(myClass.hashCode(), new SoftReference<>(myClass));
        map.put(myClass.hashCode(), new SoftReference<>(myClass));
        myClass = new MyClass("456");
        map.put(myClass.hashCode(), new SoftReference<>(myClass));

        System.out.println(map.size());

        Iterator<Entry<Integer, SoftReference<MyClass>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Integer, SoftReference<MyClass>> entry = iterator.next();
            SoftReference<MyClass> val = entry.getValue();
            MyClass item = val.get();
            if (null != item) {
                System.out.println(item.mString);
            }
            //
            // map.remove(item.hashCode());
            // Exception in thread "main" java.util.ConcurrentModificationException
            // at java.util.HashMap$HashIterator.nextNode(Unknown Source)
            // at java.util.HashMap$EntryIterator.next(Unknown Source)
            // at java.util.HashMap$EntryIterator.next(Unknown Source)
            // at test4HashMap.main(test4HashMap.java:30)
        }
        map.remove(myClass.hashCode());
        // System.out.println(map.size());
        listHashMap();
        listHashMap1();
    }

    public static class MyClass {
        String mString;

        public MyClass(String str) {
            mString = str;
        }
    }

    @SuppressWarnings("unchecked")
    public static void listHashMap1() {
        HashMap hashmap = new HashMap();
        for (int i = 0; i < 1000; i++) {
            hashmap.put("" + i, "thanks");
        }

        long bs = Calendar.getInstance().getTimeInMillis();
        Iterator iterator = hashmap.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.print(hashmap.get(iterator.next()));
        }
        System.out.println();
        System.out.println(Calendar.getInstance().getTimeInMillis() - bs);
//        listHashMap();
    }

    public static void listHashMap() {
        java.util.HashMap hashmap = new java.util.HashMap();
        for (int i = 0; i < 1000; i++) {
            hashmap.put("" + i, "thanks");
        }
        long bs = Calendar.getInstance().getTimeInMillis();
        java.util.Iterator it = hashmap.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
            // entry.getKey() 返回与此项对应的键
            // entry.getValue() 返回与此项对应的值
            System.out.print(entry.getValue());
        }
        System.out.println();
        System.out.println(Calendar.getInstance().getTimeInMillis() - bs);
    }
}
