import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Client {
    public static void main(String[] args) throws Exception {
        ReadTextFile();
    }

    static Integer  globalX = 0;


    public static void ReadTextFile ()throws IOException
    {
        System.out.println(new File("input.txt").getAbsoluteFile());
        //BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt").getAbsoluteFile()));

        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String xString= line.replaceAll("[^0-9]", "");
            globalX = Integer.parseInt(xString);


            String s ="";
            Integer value = 0;


            while ((line = br.readLine()) != null)
            {
                String inputLine= line.replace("(x)","").replace("(X)","");
                inputLine= inputLine.replace("A",CallServerA()).replace("B",CallServerB()).replace("C",CallServerC()).replace("D",CallServerD()).replace("E",CallServerE());
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                System.out.println(engine.eval(inputLine));

            }

        }
        catch (Exception ex)
        {

            System.out.println(ex);
        }
        finally{
            br.close();
        }


    }



    public static String CallServerA() throws IOException {
        return CallServer("http://localhost:8080/A?x=" + globalX);
    }

    public static String CallServerB() throws IOException {
        return CallServer("http://localhost:8080/B?x=" + globalX);
    }
    public static String CallServerC() throws IOException {
        return CallServer("http://localhost:8080/C?x=" + globalX);
    }
    public static String CallServerD() throws IOException {
        return CallServer("http://localhost:8080/D?x=" + globalX);
    }
    public static String CallServerE() throws IOException {
        return CallServer("http://localhost:8080/E?x=" + globalX);
    }

    private static String CallServer(String url) throws IOException {
        String val = "0";
        URL web = new URL(url);
        URLConnection yc = web.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
        {
            val = inputLine;
        }
        in.close();
        return val;
    }

//    A(x) x^2
//    B(x) 10x
//    C(x) x+20
//    D(x) log10 x
//    E(x) sqrt(x)

    //
    // input.txt
    //

//    x = 100
//    A(x) + B(x) + C(x)
//(B(x) * C(x)) + (D(x) * E(x))
//    A*( C(x) * E(x) )
//    E*( B*( A(x) ) )
//    D*( A*( E(x) + D(x) ) )

}
