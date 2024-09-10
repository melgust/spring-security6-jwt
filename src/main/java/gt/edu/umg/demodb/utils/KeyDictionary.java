package gt.edu.umg.demodb.utils;

public enum KeyDictionary {

	ALPHA_CAPS("ABCDEFGHIJKLMNOPQRSTUVWXYZ"), 
	ALPHA("abcdefghijklmnopqrstuvwxyz"), 
	NUMERIC("0123456789"), 
	SPECIAL_CHARS("!@#$%^&*_=+-/"),
	MIDDLE("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"),
	STRONG("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*_=+-/");
	private final String value;

	private KeyDictionary(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
	
}
