package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ReadJson {
	
	public JSONObject readJsonData(String input) throws IOException, IOException, ParseException
	{
		JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\testData\\inputData.json"));
		System.out.println("input json...........:"+jsonObject);
		return (JSONObject) jsonObject.get(input);
	}

}
