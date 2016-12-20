import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class DataSearchEngineTest {

    @Test
    public void simpleTest() {
        String fileName = "src/test/test1.txt";
        DataSearchEngine google = new DataSearchEngine();
        try {
            Data d = new FileData(fileName);
            google.indexData(d);
            String[] request = {"it", "is", "what", "it", "is"};
            LinkedList<Data> res = google.searchWords(request);
            Assert.assertTrue(res.contains(d));
            request = new String[]{"it", "is", "what", "it", "is", "asdf"};
            res = google.searchWords(request);
            Assert.assertTrue(res.size() == 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void advancedTest() {
        String fileName1 = "src/test/test1.txt";
        String fileName2 = "src/test/test2.txt";
        DataSearchEngine google = new DataSearchEngine();
        try {
            Data d1 = new FileData(fileName1);
            Data d2 = new FileData(fileName2);
            google.indexData(d1);
            google.indexData(d2);
            String[] request = {"it", "is", "what"};
            LinkedList<Data> res = google.searchWords(request);
            Assert.assertTrue(res.contains(d1) && res.contains(d2));
            request = new String[]{"it", "is", "what", "banana"};
            res = google.searchWords(request);
            Assert.assertTrue(res.size() == 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void superhardTest() {
        String fileName1 = "src/test/test1.txt";
        String fileName2 = "src/test/test2.txt";
        String fileName3 = "src/test/test3.txt";
        DataSearchEngine google = new DataSearchEngine();
        try {
            Data d1 = new FileData(fileName1);
            Data d2 = new FileData(fileName2);
            Data d3 = new FileData(fileName2);
            google.indexData(d1);
            google.indexData(d2);
            google.indexData(d3);
            String[] request = {"it", "is", "what"};
            LinkedList<Data> res = google.searchWords(request);
            Assert.assertTrue(res.contains(d1) && res.contains(d2));
            request = new String[]{"it", "is"};
            res = google.searchWords(request);
            Assert.assertTrue(res.contains(d1) && res.contains(d2) && res.contains(d3));
            request = new String[]{"it", "is", "what", "banana"};
            res = google.searchWords(request);
            Assert.assertTrue(res.size() == 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
