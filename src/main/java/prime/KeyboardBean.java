package prime;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
@Named

@SessionScoped
public class KeyboardBean {
 
   private String value;
 
   public String getValue() {
       System.out.println("KeyboardBean::reading value: " + value);
        
       return value;
   }
 
   public void setValue(String value) {
       System.out.println("KeyboardBean::setting value: " + value);
        
       this.value = value;
   }   
}
