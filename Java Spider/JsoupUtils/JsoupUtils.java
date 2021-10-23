package JsoupUtils;

import GoodsData.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class JsoupUtils {

    public static void searchInJD(String keyWords) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keyWords;
        Document document = Jsoup.parse(new URL(url), 3000);

        Elements li = document.getElementsByTag("li");
        ArrayList<Goods> goodsList = new ArrayList<>();

        for (Element element : li) {
            String price = element.getElementsByClass("p-price").eq(0).text();
            String goodName = element.getElementsByClass("p-name").eq(0).text();

            if (price.length() != 0 && goodName.length() != 0) {
                goodsList.add(new Goods(goodName, price));
            }
        }

        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }
}
