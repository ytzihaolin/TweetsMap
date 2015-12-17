package backend;
public class TweetDetail implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Properties.
    String Name;
    String Latitude;
    String T;
   	String Longtitude;

    // Getters.
    public String getName() { return Name; }
    public String getLatitude() { return Latitude; }
    public String getLongtitude() { return Longtitude; }
    public String getT() { return T; }

    // Setters.
    public void setName(String name) { this.Name = name; }
    public void setLatitude(String string) { this.Latitude = string; }
    public void setLongtitude(String d) { this.Longtitude = d; }
    public void setT(String string) { this.T = string; }

}