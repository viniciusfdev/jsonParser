/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.fasterxml.jackson.core.*;
import static com.fasterxml.jackson.core.JsonToken.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author vinicius
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String carJson = 
        "{\n" +
        "  \"bib\":{\n" +
        "    \"book\":[\n" +
        "      {\n" +
        "        \"title\":\"XML DB\",\n" +
        "        \"author\":\"John\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"title\":\"XML Search\",\n" +
        "        \"author\":\"Mike\",\n" +
        "        \"chapter\":{\n" +
        "          \"title\":\"XPath\",\n" +
        "          \"author\":\"John\"\n" +
        "        }\n" +
        "      }\n" +
        "    ]\n" +
        "  }\n" +
        "}";
        
        String json = "";
        String filePath = (new File("src/jsonfile2.json")).getAbsolutePath();
        json = new String (Files.readAllBytes(Paths.get(filePath)));

        String token = "";
        JsonToken tokenAux = null;
        JsonToken jsonToken = null;
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);
        
        while(!parser.isClosed()){
            jsonToken = parser.nextToken();
            token = parser.getText();
            if(jsonToken == FIELD_NAME){
                System.out.println("call start element:"+parser.getText());
                jsonToken = parser.nextToken(); 
                if(jsonToken != null && 
                  (jsonToken != START_OBJECT &&
                   jsonToken != START_ARRAY)){
                    System.out.println("call characters: "+parser.getText());
                    System.out.println("call end element: "+token);
                }
            }
            else if(jsonToken == END_OBJECT){
                if(parser.getCurrentName() != null)
                    System.out.println("call end element: "+parser.getCurrentName());
            }else if(jsonToken == END_ARRAY){
                if(parser.getCurrentName() != null)
                    System.out.println("call end element: "+parser.getCurrentName());
            }
            else if(jsonToken == null){
                break;
            }
            else if(jsonToken == VALUE_TRUE || jsonToken == VALUE_FALSE ||
                    jsonToken == VALUE_NUMBER_FLOAT || 
                    jsonToken == VALUE_NUMBER_INT || jsonToken == VALUE_STRING){
                System.out.println("call characters: "+parser.getText());
            }
            
        }
    }
    
}