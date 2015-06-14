package elements;

public enum TechSkills {
    JAVA("Java"), CPP("C++"), SQL("SQL"), ORACLE("Oracle SQL"), OOP(
	    "Object-Oriented Programming"), APACHE("Apache server");

    private String value;

    private TechSkills(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return value;
    }
}
