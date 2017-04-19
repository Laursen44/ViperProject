package Serialization;

import java.util.Random;

public class TestClass 
{
	static Random random = new Random();
	
	public static void main(String[] args)
	{
		int[] data = new int[50];
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
		database.serializeToFile("test.vpdb");
		
	}
}
