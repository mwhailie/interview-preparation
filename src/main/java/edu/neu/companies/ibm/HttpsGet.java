package edu.neu.companies.ibm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class HttpsGet {

    OkHttpClient client = new OkHttpClient();

    public String getJSON(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    class ResponseData {
        private List<Country> data;
        ResponseData() {
            // no-args constructor
        }


        @Override
        public String toString(){
            return data.toString();
        }
    }
    class Country {
        private String name = "name";
        private String alpha2Code = "AF";
        private int population = 0;
        Country() {
            // no-args constructor
        }
        @Override
        public String toString(){
            return "[name:" + name + ", alpha2Code:" + alpha2Code + ", population: " + population + " ]";
        }
    }

    public List<Country> parseJson(String serializedJSON) throws IOException {
        Gson gson = new Gson();
//        serializedJSON = serializedJSON.substring(serializedJSON.indexOf("["));
//        System.out.println(serializedJSON);
//        serializedJSON = "[{\"name\":\"Chad\",\"nativeName\":\"Tchad\",\"topLevelDomain\":[\".td\"],\"alpha2Code\":\"TD\",\"numericCode\":\"148\",\"alpha3Code\":\"TCD\",\"currencies\":[\"XAF\"],\"callingCodes\":[\"235\"],\"capital\":\"N'Djamena\",\"altSpellings\":[\"TD\",\"Tchad\",\"Republic of Chad\",\"République du Tchad\"],\"relevance\":\"0\",\"region\":\"Africa\",\"subregion\":\"Middle Africa\",\"language\":[\"French\",\"Arabic\"],\"languages\":[\"fr\",\"ar\"],\"translations\":{\"de\":\"Tschad\",\"es\":\"Chad\",\"fr\":\"Tchad\",\"it\":\"Ciad\",\"ja\":\"チャド\",\"nl\":\"Tsjaad\",\"hr\":\"Čad\"},\"population\":14037000,\"latlng\":[15,19],\"demonym\":\"Chadian\",\"borders\":[\"CMR\",\"CAF\",\"LBY\",\"NER\",\"NGA\",\"SSD\"],\"area\":1284000,\"gini\":39.8,\"timezones\":[\"UTC+01:00\"]},{\"name\":\"Chile\",\"nativeName\":\"Chile\",\"topLevelDomain\":[\".cl\"],\"alpha2Code\":\"CL\",\"numericCode\":\"152\",\"alpha3Code\":\"CHL\",\"currencies\":[\"CLF\",\"CLP\"],\"callingCodes\":[\"56\"],\"capital\":\"Santiago\",\"altSpellings\":[\"CL\",\"Republic of Chile\",\"República de Chile\"],\"relevance\":\"0\",\"region\":\"Americas\",\"subregion\":\"South America\",\"language\":[\"Spanish\"],\"languages\":[\"es\"],\"translations\":{\"de\":\"Chile\",\"es\":\"Chile\",\"fr\":\"Chili\",\"it\":\"Cile\",\"ja\":\"チリ\",\"nl\":\"Chili\",\"hr\":\"Čile\"},\"population\":18006407,\"latlng\":[-30,-71],\"demonym\":\"Chilean\",\"borders\":[\"ARG\",\"BOL\",\"PER\"],\"area\":756102,\"gini\":52.1,\"timezones\":[\"UTC-06:00\",\"UTC-04:00\"]},{\"name\":\"China\",\"nativeName\":\"中国\",\"topLevelDomain\":[\".cn\"],\"alpha2Code\":\"CN\",\"numericCode\":\"156\",\"alpha3Code\":\"CHN\",\"currencies\":[\"CNY\"],\"callingCodes\":[\"86\"],\"capital\":\"Beijing\",\"altSpellings\":[\"CN\",\"Zhōngguó\",\"Zhongguo\",\"Zhonghua\",\"People's Republic of China\",\"中华人民共和国\",\"Zhōnghuá Rénmín Gònghéguó\"],\"relevance\":\"0\",\"region\":\"Asia\",\"subregion\":\"Eastern Asia\",\"language\":[\"Standard Chinese\"],\"languages\":[\"zh\"],\"translations\":{\"de\":\"China\",\"es\":\"China\",\"fr\":\"Chine\",\"it\":\"Cina\",\"ja\":\"中国\",\"nl\":\"China\",\"hr\":\"Kina\"},\"population\":1371590000,\"latlng\":[35,105],\"demonym\":\"Chinese\",\"borders\":[\"AFG\",\"BTN\",\"MMR\",\"HKG\",\"IND\",\"KAZ\",\"PRK\",\"KGZ\",\"LAO\",\"MAC\",\"MNG\",\"PAK\",\"RUS\",\"TJK\",\"VNM\"],\"area\":9640011,\"gini\":47,\"timezones\":[\"UTC+08:00\"]},{\"name\":\"Christmas Island\",\"nativeName\":\"Christmas Island\",\"topLevelDomain\":[\".cx\"],\"alpha2Code\":\"CX\",\"numericCode\":\"162\",\"alpha3Code\":\"CXR\",\"currencies\":[\"AUD\"],\"callingCodes\":[\"61\"],\"capital\":\"Flying Fish Cove\",\"altSpellings\":[\"CX\",\"Territory of Christmas Island\"],\"relevance\":\"0.5\",\"region\":\"Oceania\",\"subregion\":\"Australia and New Zealand\",\"language\":[\"English\"],\"languages\":[\"en\"],\"translations\":{\"fr\":\"Île Christmas\",\"nl\":\"Christmaseiland\",\"de\":\"Weihnachtsinsel\",\"it\":\"Isola di Natale\",\"ja\":\"クリスマス島\",\"es\":\"Isla de Navidad\",\"hr\":\"Božićni otok\"},\"population\":2072,\"latlng\":[-10.5,105.66666666],\"demonym\":\"Christmas Island\",\"borders\":[],\"area\":135,\"timezones\":[\"UTC+07:00\"]},{\"name\":\"Czech Republic\",\"nativeName\":\"Česká republika\",\"topLevelDomain\":[\".cz\"],\"alpha2Code\":\"CZ\",\"numericCode\":\"203\",\"alpha3Code\":\"CZE\",\"currencies\":[\"CZK\"],\"callingCodes\":[\"420\"],\"capital\":\"Prague\",\"altSpellings\":[\"CZ\",\"Česká republika\",\"Česko\"],\"relevance\":\"0\",\"region\":\"Europe\",\"subregion\":\"Eastern Europe\",\"language\":[\"Czech\"],\"languages\":[\"cs\",\"sk\"],\"translations\":{\"de\":\"Tschechische Republik\",\"es\":\"República Checa\",\"fr\":\"République tchèque\",\"it\":\"Repubblica Ceca\",\"ja\":\"チェコ\",\"nl\":\"Tsjechië\",\"hr\":\"Češka\"},\"population\":10537818,\"latlng\":[49.75,15.5],\"demonym\":\"Czech\",\"borders\":[\"AUT\",\"DEU\",\"POL\",\"SVK\"],\"area\":78865,\"gini\":26,\"timezones\":[\"UTC+01:00\"]},{\"name\":\"French Guiana\",\"nativeName\":\"Guyane française\",\"topLevelDomain\":[\".gf\"],\"alpha2Code\":\"GF\",\"numericCode\":\"254\",\"alpha3Code\":\"GUF\",\"currencies\":[\"EUR\"],\"callingCodes\":[\"594\"],\"capital\":\"Cayenne\",\"altSpellings\":[\"GF\",\"Guiana\",\"Guyane\"],\"relevance\":\"0\",\"region\":\"Americas\",\"subregion\":\"South America\",\"language\":[\"French\"],\"languages\":[\"fr\"],\"translations\":{\"de\":\"Französisch Guyana\",\"es\":\"Guayana Francesa\",\"fr\":\"Guayane\",\"it\":\"Guyana francese\",\"ja\":\"フランス領ギアナ\",\"nl\":\"Frans-Guyana\",\"hr\":\"Francuska Gvajana\"},\"population\":239648,\"latlng\":[4,-53],\"demonym\":\"\",\"borders\":[\"BRA\",\"SUR\"],\"timezones\":[\"UTC-03:00\"]},{\"name\":\"French Polynesia\",\"nativeName\":\"Polynésie française\",\"topLevelDomain\":[\".pf\"],\"alpha2Code\":\"PF\",\"numericCode\":\"258\",\"alpha3Code\":\"PYF\",\"currencies\":[\"XPF\"],\"callingCodes\":[\"689\"],\"capital\":\"Papeetē\",\"altSpellings\":[\"PF\",\"Polynésie française\",\"French Polynesia\",\"Pōrīnetia Farāni\"],\"relevance\":\"0\",\"region\":\"Oceania\",\"subregion\":\"Polynesia\",\"language\":[\"French\"],\"languages\":[\"fr\"],\"translations\":{\"de\":\"Französisch-Polynesien\",\"es\":\"Polinesia Francesa\",\"fr\":\"Polynésie française\",\"it\":\"Polinesia Francese\",\"ja\":\"フランス領ポリネシア\",\"nl\":\"Frans-Polynesië\",\"hr\":\"Francuska Polinezija\"},\"population\":268270,\"latlng\":[-15,-140],\"demonym\":\"French Polynesian\",\"borders\":[],\"area\":4167,\"timezones\":[\"UTC-10:00\",\"UTC-09:30\",\"UTC-09:00\"]},{\"name\":\"French Southern and Antarctic Lands\",\"nativeName\":\"Territoire des Terres australes et antarctiques françaises\",\"topLevelDomain\":[\".tf\"],\"alpha2Code\":\"TF\",\"numericCode\":\"260\",\"alpha3Code\":\"ATF\",\"currencies\":[\"EUR\"],\"callingCodes\":[\"\"],\"capital\":\"Port-aux-Français\",\"altSpellings\":[\"TF\"],\"relevance\":\"0\",\"region\":\"Africa\",\"subregion\":\"Southern Africa\",\"language\":[\"French\"],\"languages\":[\"fr\"],\"translations\":{\"de\":\"Französische Süd- und Antarktisgebiete\",\"es\":\"Tierras Australes y Antárticas Francesas\",\"fr\":\"Terres australes et antarctiques françaises\",\"it\":\"Territori Francesi del Sud\",\"ja\":\"フランス領南方・南極地域\",\"nl\":\"Franse Gebieden in de zuidelijke Indische Oceaan\",\"hr\":\"Francuski južni i antarktički teritoriji\"},\"population\":140,\"latlng\":[-49.25,69.167],\"demonym\":\"French\",\"borders\":[],\"area\":7747,\"timezones\":[\"UTC+05:00\"]},{\"name\":\"Liechtenstein\",\"nativeName\":\"Liechtenstein\",\"topLevelDomain\":[\".li\"],\"alpha2Code\":\"LI\",\"numericCode\":\"438\",\"alpha3Code\":\"LIE\",\"currencies\":[\"CHF\"],\"callingCodes\":[\"423\"],\"capital\":\"Vaduz\",\"altSpellings\":[\"LI\",\"Principality of Liechtenstein\",\"Fürstentum Liechtenstein\"],\"relevance\":\"0\",\"region\":\"Europe\",\"subregion\":\"Western Europe\",\"language\":[\"German\"],\"languages\":[\"de\"],\"translations\":{\"de\":\"Liechtenstein\",\"es\":\"Liechtenstein\",\"fr\":\"Liechtenstein\",\"it\":\"Liechtenstein\",\"ja\":\"リヒテンシュタイン\",\"nl\":\"Liechtenstein\",\"hr\":\"Lihtenštajn\"},\"population\":37370,\"latlng\":[47.26666666,9.53333333],\"demonym\":\"Liechtensteiner\",\"borders\":[\"AUT\",\"CHE\"],\"area\":160,\"timezones\":[\"UTC+01:00\"]},{\"name\":\"Seychelles\",\"nativeName\":\"Seychelles\",\"topLevelDomain\":[\".sc\"],\"alpha2Code\":\"SC\",\"numericCode\":\"690\",\"alpha3Code\":\"SYC\",\"currencies\":[\"SCR\"],\"callingCodes\":[\"248\"],\"capital\":\"Victoria\",\"altSpellings\":[\"SC\",\"Republic of Seychelles\",\"Repiblik Sesel\",\"République des Seychelles\"],\"relevance\":\"0.5\",\"region\":\"Africa\",\"subregion\":\"Eastern Africa\",\"language\":[\"French\",\"English\",\"Seychellois Creole\"],\"languages\":[\"fr\",\"en\"],\"translations\":{\"de\":\"Seychellen\",\"es\":\"Seychelles\",\"fr\":\"Seychelles\",\"it\":\"Seychelles\",\"ja\":\"セーシェル\",\"nl\":\"Seychellen\",\"hr\":\"Sejšeli\"},\"population\":89949,\"latlng\":[-4.58333333,55.66666666],\"demonym\":\"Seychellois\",\"borders\":[],\"area\":452,\"gini\":65.8,\"timezones\":[\"UTC+04:00\"]}]\n";
        ResponseData obj = gson.fromJson(serializedJSON, ResponseData.class);
//        List<Country> obj =  gson.fromJson(serializedJSON, new TypeToken<List<Country>>(){}.getType());
        return obj.data;
    }

    public static String getHTML(String urlToRead) throws Exception {

        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append("\n" + line);
        }
        rd.close();
        return result.toString();
    }

    public static void main(String[] args) throws Exception
    {
        HttpsGet h = new HttpsGet();
        String json = h.getJSON("https://jsonmock.hackerrank.com/api/countries/search?name=ch");

        System.out.println(json);
        List<Country> countries =  h.parseJson(json);
        for(Country c :countries ){
            System.out.println(c);
        }

    }
}
