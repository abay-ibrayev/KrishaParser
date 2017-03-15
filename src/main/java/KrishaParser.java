import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abay on 2/26/17.
 */
public class KrishaParser {
    private static final String URL = "https://krisha.kz/prodazha/kvartiry/almaty/?page=";
    private static final String BASE_URL = "https://krisha.kz";

    public static void main(String[] args) throws IOException {
        for (int j = 1; j < 10; j++) {
            List<String> links = new ArrayList<String>();
            Document doc = Jsoup.connect(URL + j).timeout(10 * 1000).get();
            for (Element section : doc.select("section[class=a-list a-search-list a-list-with-favs]")) {
                for (Element order : section.select("div[class=a-description]")) {
                    Elements tds = order.select("a[class=link]");
                    links.add(tds.attr("href"));
                }

            }
            for (String url : links) {
                Document linker = Jsoup.connect(BASE_URL + url).timeout(10 * 1000).get();

//                for (Element table : linker.select("div[class=main-col a-item]")) {
//                    //get prices
//                    for (Element ta : table.select("div[class=a-header-wrapper]")) {
//                        Elements price = ta.select("span[class=price]");
//                        System.out.println(price.text().substring(0,price.text().length()-2));
////                        Elements room = ta.select("h1");
////                        System.out.println(room.text().split(",")[0].substring(0, 1));
//                    }
//           }
//              //get region
//            for (Element region : linker.select("div[class=a-where-region]")){
//                System.out.println(region.text());
//            }
                for (Element output : linker.select("div[class=col-sm-6 col-xs-6 a-description]")){
                    for (Element params : output.select("dl[class=a-parameters]")){
                        Elements floor = params.select("dt[data-name=flat.floor]");
                        System.out.println(floor.next().text().split("\\s+")[0]);
                        //Elements square =params.select("dt[data-name=live.square]");
                        //System.out.println(square.next().text().split(",")[0].substring(0,square.next().text().split(",")[0].length()-2));
                    }
                }
                }


            }

        }

}
