package Serialization;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class TestClass 
{
	static Random random = new Random();
	
	public static void main(String[] args)
	{
		int[] data = new int[50000];
		for (int i = 0; i < data.length; i++)
		{
			data[i] = random.nextInt();
		}
		
		VPDatabase database = new VPDatabase("database");
		
		VPArray array = VPArray.Integer("numbers", data);
		VPField field = VPField.Integer("int", 8);
		
		VPObject object = new VPObject("entity");
		object.addArray(array);
		object.addField(field);
		
		database.addObject(object);
		
		byte[] stream = new byte[database.getSize()];
		database.getBytes(stream, 0);
		saveToFile("test.vpdb", stream);
		
	}
	
	static void saveToFile(String path, byte[] data)
	{
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
			stream.write(data);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
