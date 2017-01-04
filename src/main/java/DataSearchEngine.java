import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DataSearchEngine {

    private Map<String, SortedSet<DataSource>> words;

    private DataSearchEngine() {
        words = new HashMap<String, SortedSet<DataSource>>();
    }

    public static class Builder {

        private DataSearchEngine engine;

        Builder(DataSource[] data) {
            engine = new DataSearchEngine();
            for(DataSource d : data)
                indexData(d);
        }

        DataSearchEngine getEngine() {
            return engine;
        }

        private void indexData(DataSource d) {
            try {
                String word;
                InputStreamReader str = new InputStreamReader(d.getData());
                String text = "";
                int b;
                while ((b = str.read()) != -1) {
                    text += (char) b;
                }
                StringTokenizer textTokens = new StringTokenizer(text);
                while (textTokens.hasMoreElements()) {
                    word = textTokens.nextToken();
                    SortedSet<DataSource> pages;
                    if (engine.words.containsKey(word))
                        pages = engine.words.get(word);
                    else
                        pages = new TreeSet<DataSource>();
                    pages.add(d);
                    engine.words.put(word, pages);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public SortedSet<DataSource> searchWords(String[] request) {
        if (words.get(request[0]) == null)
            return new TreeSet<DataSource>();
        SortedSet<DataSource> result = new TreeSet<DataSource>(words.get(request[0]));
        for (String word : request) {
            if (words.containsKey(word))
                result = intersection(result, words.get(word));
            else
                return new TreeSet<DataSource>();
        }
        return result;
    }

    private static SortedSet<DataSource> intersection(SortedSet<DataSource> a, SortedSet<DataSource> b) {
        if(a.size() == 0 || b.size() == 0)
            return new TreeSet<DataSource>();
        Iterator<DataSource> ai = a.iterator();
        Iterator<DataSource> bi = b.iterator();
        SortedSet<DataSource> result = new TreeSet<DataSource>();
        DataSource ad = ai.next();
        DataSource bd = bi.next();
        while (ai.hasNext() && bi.hasNext()) {
            if (ad.compareTo(bd) > 0) {
                ad = ai.next();
            } else if (ad.compareTo(bd) < 0) {
                bd = bi.next();
            } else if (ad.compareTo(bd) == 0) {
                result.add(ad);
                ad = ai.next();
                bd = bi.next();
            }
        }
        if (ad.compareTo(bd) == 0)
            result.add(ad);
        return result;
    }

}
