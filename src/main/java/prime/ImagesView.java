package prime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named
public class ImagesView implements Serializable {

	private List<String> images;
	private Map<String, String>map = new HashMap<>();
	
	@PostConstruct
	public void init() {
		images = new ArrayList<String>();

		
		
		for (int i = 1; i <= 4; i++) {
			images.add("slide" + i + ".jpg");
			String imageSrc = "slide" + i + ".jpg";
			String imageLink = "g" + i +".jsf?hra=" + i; // key value
			map.put(imageSrc, imageLink);
		}
	}

	public List<String> getImages() {
		return images;
	}
	
	public String getLink(String imageSrc){
		return map.get(imageSrc);
//		for(Entry<String, String> entry : map.entrySet()) {
//		    if(imageSrc.equals(entry.getKey())){
//		    	return entry.getValue();
//		    }
//	
//		}
//		return null;
	}
	

}