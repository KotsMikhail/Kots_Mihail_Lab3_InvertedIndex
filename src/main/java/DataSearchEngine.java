import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class DataSearchEngine {

    private HashMap<String, LinkedList<Data>> words;

    DataSearchEngine() {
        words = new HashMap<String, LinkedList<Data>>();
    }

    public void indexData(Data d) {
        String word;
        try {
            while (!(word = d.getNextWord()).equals("")) {
                LinkedList<Data> pages;
                if (words.containsKey(word))
                    pages = words.get(word);
                else
                    pages = new LinkedList<Data>();
                if (!pages.contains(d))
                    pages.add(d);
                words.put(word, pages);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Data> searchWords(String[] request) {
        LinkedList<Data> result = new LinkedList<Data>(words.get(request[0]));
        for (String word : request) {
            if (words.containsKey(word))
                result = intersection(result, words.get(word));
            else
                return new LinkedList<Data>();
        }
        return result;
    }

    private static LinkedList<Data> intersection(LinkedList<Data> a, LinkedList<Data> b) {
        ListIterator<Data> ai = a.listIterator();
        ListIterator<Data> bi = b.listIterator();
        LinkedList<Data> result = new LinkedList<Data>();
        while (ai.hasNext() && bi.hasNext()) {
            Data ad = ai.next();
            Data bd = bi.next();
            if (ad.compareTo(bd) > 0) {
                bi.previous();
            } else if (ad.compareTo(bd) < 0) {
                ai.previous();
            } else if (ad.compareTo(bd) == 0) {
                result.add(ad);
            }
        }
        return result;
    }

}
