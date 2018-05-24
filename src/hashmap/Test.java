package hashmap;

public class Test {
	public static void main(String[] args) {
		MyHashMap map = new MyHashMap();
		//size()
		System.out.println(map.size());
		map.put("Hao", 1);
		map.put("tian", 2);
		map.put("Chen", 10);
		//put --> size(), put --> update
		System.out.println(map.size());
		System.out.println(map.put("Chen", 4));
		//test isEmpty() and clear()
		System.out.println(map.isEmpty());
		map.clear();
		System.out.println(map.size());
		//containsKey()
		map.put("cao", 11);
		map.put("nima", 23);
		map.put("123", 13);
		System.out.println(map.containsKey("cao"));
		System.out.println(map.get("nima"));
		System.out.println(map.remove("nima"));//remove ("nima", 23)
		System.out.println(map.size());
		System.out.println(map.get("nima"));// has been removed
		
		//test rehash
		map.put("cao1", 11);
		map.put("nima1", 23);
		map.put("1232", 13);
		map.put("cao3", 11);
		map.put("nima3", 23);
		map.put("1232", 13);
		map.put("cao3", 11);
		map.put("nima1", 23);
		map.put("1213", 13);
		map.put("nima3", 23);
		map.put("ccc", 13);
		map.put("a", 11);
		map.put("niasd1", 23);
		map.put("1asd", 13);
		//assess entry[] to be public just test
		System.out.println(map.size());
		System.out.println(map.array.length);
	}
}
